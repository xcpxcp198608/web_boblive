package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.dao.auth.AuthDealerDao;
import com.wiatec.boblive.orm.dao.auth.AuthSalesDao;
import com.wiatec.boblive.orm.dao.auth.AuthorizationDao;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import com.wiatec.boblive.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * authorization service
 * @author patrick
 */
@Service
public class Authorization1Service extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AuthorizationDao authorizationDao;

    @Resource
    private AuthDealerDao authDealerDao;

    @Resource
    private AuthSalesDao authSalesDao;


    /**
     * activate or deactivate
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo activateOrDeactivate(HttpServletRequest request,
                                           AuthorizationInfo authorizationInfo,
                                           String manager, boolean active){
        if("leader".equals(manager)){
            authorizationInfo.setLeader(getCurrentUserName(request));
        }else if("dealer".equals(manager)){
            authorizationInfo.setDealer(getCurrentUserName(request));
        }else if("sales".equals(manager)){
            authorizationInfo.setSales(getCurrentUserName(request));
        }
        try {
            if(active) {
                authorizationDao.activate(authorizationInfo);
            }else{
                authorizationDao.deactivate(authorizationInfo);
            }
        }catch (Exception e){
            throw new XException(EnumResult.ERROR_EXECUTE_FAIL);
        }
        if(active) {
            return ResultMaster.success();
        }else{
            return ResultMaster.success(201);
        }
    }

    /**
     * update level
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateLevel(HttpServletRequest request,
                                  AuthorizationInfo authorizationInfo,
                                  int days){
        try{
            AuthorizationInfo authorizationInfo1 = authorizationDao.selectOneById(authorizationInfo);
            long addTime = days * 86400000L;
            long newTime ;
            if(authorizationInfo1.getMemberTime() <= 0){
                newTime = System.currentTimeMillis() + addTime;
            }else{
                newTime = authorizationInfo1.getMemberTime() + addTime;
            }
            authorizationInfo1.setMemberTime(newTime);
            authorizationInfo1.setMemberDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(newTime)));
            authorizationInfo1.setLevel(authorizationInfo.getLevel());
            authorizationDao.updateLevel(authorizationInfo1);
            return ResultMaster.success(authorizationInfo1);
        }catch (XException e){
            logger.error("XException: ", e);
            return ResultMaster.error(e.getCode(), e.getMessage());
        }catch (Exception e){
            logger.error("Exception", e);
            return ResultMaster.error(1001, "server inner error");
        }
    }

    /**
     * update temporary
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateTemporary(HttpServletRequest request,
                                                         @ModelAttribute AuthorizationInfo authorizationInfo){
        try{
            authorizationDao.updateTemporary(authorizationInfo);
            AuthorizationInfo authorizationInfo1 = null;
            if(!TextUtil.isEmpty(authorizationInfo.getKey())){
                authorizationInfo1 = authorizationDao.selectOneByKey(authorizationInfo);
            }
            return ResultMaster.success(authorizationInfo1);
        }catch (XException e){
            logger.error("XException: ", e);
            return ResultMaster.error(e.getCode(), e.getMessage());
        }catch (Exception e){
            logger.error("Exception", e);
            return ResultMaster.error(1001, "server inner error");
        }
    }

    /**
     * list active
     */
    public List<AuthorizationInfo> listActive(HttpServletRequest request, AuthorizationInfo authorizationInfo){
        String manager = getCurrentUserName(request);
        if(manager.startsWith("L")){
            authorizationInfo.setLeader(manager);
        }
        if(manager.startsWith("D")){
            authorizationInfo.setDealer(manager);
            authorizationInfo.setLeader(authDealerDao.getLeader(manager));
        }
        if(manager.startsWith("S")){
            authorizationInfo.setSales(manager);
            String dealer = authSalesDao.getDealer(manager);
            authorizationInfo.setDealer(dealer);
            authorizationInfo.setLeader(authDealerDao.getLeader(dealer));
        }

        if(!TextUtil.isEmpty(authorizationInfo.getMemberDate())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = simpleDateFormat.parse(authorizationInfo.getMemberDate());
                authorizationInfo.setMemberTime(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!TextUtil.isEmpty(authorizationInfo.getActiveDate())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = simpleDateFormat.parse(authorizationInfo.getActiveDate());
                authorizationInfo.setActiveTime(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println(authorizationInfo);
        List<AuthorizationInfo> authorizationInfoList = authorizationDao.selectActive(authorizationInfo);
        Map<String, HttpSession> sessionMap = SessionListener.keySessionMap;
        if(authorizationInfoList != null && authorizationInfoList.size() > 0){
            for(AuthorizationInfo authorizationInfo1 : authorizationInfoList) {
                if (sessionMap.containsKey(authorizationInfo1.getKey())) {
                    authorizationInfo1.setOnline(true);
                }
            }
        }
        return authorizationInfoList;
    }

    /**
     * list negative
     */
    public List<AuthorizationInfo> listNegative(HttpServletRequest request, AuthorizationInfo authorizationInfo){
        String manager = getCurrentUserName(request);
        if(manager.startsWith("L")){
            authorizationInfo.setLeader(manager);
        }
        if(manager.startsWith("D")){
            authorizationInfo.setDealer(manager);
        }
        if(manager.startsWith("S")){
            authorizationInfo.setSales(manager);
        }
        return authorizationDao.selectNegative(authorizationInfo);
    }

    /**
     * crate auth
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> createAuth(HttpServletRequest request, AuthorizationInfo authorizationInfo, int count){
        String manager = getCurrentUserName(request);
        if(manager.startsWith("L")){
            authorizationInfo.setLeader(manager);
        }
        if (manager.startsWith("D")){
            authorizationInfo.setDealer(manager);
            authorizationInfo.setLeader(authDealerDao.getLeader(manager));
        }
        Map<String, Object> paramsMap = new HashMap<>();
        List<String> keyList = new ArrayList<>();
        for(int i = 0 ; i < count ; i ++){
            keyList.add(TokenUtil.createKey(i));
        }
        paramsMap.put("keyList", keyList);
        paramsMap.put("authorizationInfo", authorizationInfo);
        authorizationDao.insertBathKey(paramsMap);
        return keyList;
    }
}
