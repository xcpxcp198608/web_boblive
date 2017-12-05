package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.dao.auth.AuthDealerDao;
import com.wiatec.boblive.orm.dao.auth.AuthSalesDao;
import com.wiatec.boblive.orm.dao.auth.AuthorizationDao;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import com.wiatec.boblive.service.BaseService;
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
 */
@Service
public class Authorization1Service extends BaseService {

    @Resource
    private AuthorizationDao authorizationDao;

    @Resource
    private AuthDealerDao authDealerDao;

    @Resource
    private AuthSalesDao authSalesDao;


    /**
     * activate or deactivate
     * @param request
     * @param authorizationInfo
     * @param manager
     * @param active
     * @return
     */
    @Transactional
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
            return new ResultInfo(ResultInfo.CODE_SERVER_ERROR, ResultInfo.STATUS_SERVER_ERROR, "execute failure");
        }
        if(active) {
            return new ResultInfo(ResultInfo.CODE_ACTIVATE_OK, ResultInfo.STATUS_OK, "activate success");
        }else{
            return new ResultInfo(ResultInfo.CODE_DEACTIVATE_OK, ResultInfo.STATUS_OK, "deactivate success");
        }
    }

    /**
     * update level
     * @param request
     * @param authorizationInfo
     * @param days
     * @return
     */
    @Transactional
    public ResultInfo<AuthorizationInfo> updateLevel(HttpServletRequest request,
                                  AuthorizationInfo authorizationInfo,
                                  int days){
        ResultInfo<AuthorizationInfo> resultInfo = new ResultInfo<>();
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
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("update successfully");
            resultInfo.setObj(authorizationInfo1);
            return resultInfo;
        }catch (Exception e){
            resultInfo.setCode(ResultInfo.CODE_SERVER_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_SERVER_ERROR);
            resultInfo.setMessage("update failure");
            return resultInfo;
        }
    }

    /**
     * update temporary
     * @param request
     * @param authorizationInfo
     * @return
     */
    @Transactional
    public ResultInfo<AuthorizationInfo> updateTemporary(HttpServletRequest request,
                                                         @ModelAttribute AuthorizationInfo authorizationInfo){
        ResultInfo<AuthorizationInfo> resultInfo = new ResultInfo<>();
        try{
            authorizationDao.updateTemporary(authorizationInfo);
            AuthorizationInfo authorizationInfo1;
            if(!TextUtil.isEmpty(authorizationInfo.getKey())){
                authorizationInfo1 = authorizationDao.selectOneByKey(authorizationInfo);
                resultInfo.setObj(authorizationInfo1);
            }
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("update successfully");
            return resultInfo;
        }catch (Exception e){
            resultInfo.setCode(ResultInfo.CODE_SERVER_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_SERVER_ERROR);
            resultInfo.setMessage("update failure");
            return resultInfo;
        }
    }

    /**
     * list active
     * @param request
     * @param authorizationInfo
     * @return
     */
    @Transactional(readOnly = true)
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
     * @param request
     * @return
     */
    @Transactional(readOnly = true)
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
     * @param request
     * @param count
     * @return
     */
    @Transactional
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
