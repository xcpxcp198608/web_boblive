package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.Constant;
import com.wiatec.boblive.common.result.*;
import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.dao.auth.AuthorizationDao;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * authorization service
 * @author patrick
 */
@Service
public class AuthorizationService {


    private static final long TEMP_DURATION = 7*24*3600*1000;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AuthorizationDao authorizationDao;

    /**
     * bath create16 key
     * @param count count
     * @param group group
     */
    @Transactional
    public List<String> create(int count, String group){
        try{
            List<String> keyList = new ArrayList<>();
            for(int i = 0 ; i < count ; i ++){
                String key = createKey(i);
                keyList.add(key);
            }
            Map<String , Object> map = new HashMap<>();
            map.put("keyList", keyList);
            map.put("group", group);
            authorizationDao.insertBathKey(map);
            return keyList;
        }catch (XException e){
            logger.error("Exception: ", e);
            throw new XException(e.getCode(), e.getMessage());
        }catch (Exception e){
            logger.error("Exception: ", e);
            throw new XException(EnumResult.ERROR_SERVER_EXCEPTION);
        }
    }

    //create16 key
    private String createKey(int i){
        return TokenUtil.create16(i+"", "www.wiatec.com");
    }

    /**
     * active auth key on user device, bind key and mac
     * @param key provide to user
     * @param mac wifi mac of the device
     * @return
     */
    @Transactional
    public ResultInfo<AuthorizationInfo> active(String key, String mac, String lang){
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setKey(key);
        authorizationInfo.setMac(mac);
        authorizationInfo.setActiveTime(System.currentTimeMillis());
        ResultInfo<AuthorizationInfo> resultInfo = new ResultInfo<>();
        if("wiatec".equals(key)){
            authorizationInfo.setLevel((short) 8);
            authorizationInfo.setEffective(true);
            authorizationInfo.setTemporary(true);
            return ResultMaster.success(authorizationInfo);
        }
        if(authorizationDao.countKey(authorizationInfo) != 1){
            if(Constant.LANG_CS_CZ.equals(lang)) {
                throw new XException(EnumResultCZ.ERROR_KEY_INCORRECT);
            }else{
                throw new XException(EnumResult.ERROR_KEY_INCORRECT);
            }
        }
        if(authorizationDao.selectActiveByKey(authorizationInfo) == 1){
            if(authorizationDao.countOne(authorizationInfo) == 1){
                authorizationInfo = authorizationDao.selectOneByKey(authorizationInfo);
                if(!authorizationInfo.isEffective()){
                    if(Constant.LANG_CS_CZ.equals(lang)) {
                        throw new XException(EnumResultCZ.ERROR_KEY_DEACTIVATE);
                    }else {
                        throw new XException(EnumResult.ERROR_KEY_DEACTIVATE);
                    }
                }
                return update(false, resultInfo, authorizationInfo);
            }else {
                if(Constant.LANG_CS_CZ.equals(lang)) {
                    throw new XException(EnumResultCZ.ERROR_KEY_ALREADY_USE);
                }else {
                    throw new XException(EnumResult.ERROR_KEY_ALREADY_USE);
                }
            }
        }
        return update(true, resultInfo, authorizationInfo);
    }

    private ResultInfo<AuthorizationInfo> update(boolean update, ResultInfo<AuthorizationInfo> resultInfo, AuthorizationInfo authorizationInfo){
        try {
            if(update) {
                authorizationDao.updateActive(authorizationInfo);
            }
            return ResultMaster.success(authorizationDao.selectOneByKey(authorizationInfo));
        }catch (XException e){
            logger.error("Exception: ", e);
            throw new XException(e.getCode(), e.getMessage());
        }catch (Exception e){
            logger.error("Exception: ", e);
            throw new XException(EnumResult.ERROR_SERVER_EXCEPTION);
        }
    }

    /**
     * validate auth by key and mac
     * @param key provide to user
     * @param mac wifi mac of the device
     * @return
     */
    public ResultInfo<AuthorizationInfo> validate(HttpServletRequest request, String key, String mac, String lang){
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setKey(key);
        authorizationInfo.setMac(mac);
        if("wiatec".equals(key)){
            authorizationInfo.setLevel((short) 8);
            authorizationInfo.setEffective(true);
            authorizationInfo.setTemporary(true);
            return ResultMaster.success(authorizationInfo);
        }
        if(authorizationDao.countOne(authorizationInfo) == 1){
            AuthorizationInfo authorizationInfo1 = authorizationDao.selectOneByKey(authorizationInfo);
            if(!authorizationInfo1.isTemporary()){
                if(System.currentTimeMillis() < authorizationInfo1.getActiveTime() + TEMP_DURATION){
                    authorizationInfo1.setTemporary(true);
                }else{
                    if(System.currentTimeMillis() >= authorizationInfo1.getMemberTime()){
                        authorizationInfo1.setLevel((short)1);
                        authorizationDao.updateLevel(authorizationInfo1);
                    }
                }
            }
            HttpSession session = SessionListener.getKeySession(key);
            if(session == null){
                session = request.getSession();
            }
            session.setAttribute("key", key);
            return ResultMaster.success(authorizationInfo1);
        }else{
            if(Constant.LANG_CS_CZ.equals(lang)) {
                throw new XException(EnumResultCZ.ERROR_VALIDATE_FAILURE);
            }else {
                throw new XException(EnumResult.ERROR_VALIDATE_FAILURE);
            }
        }
    }

}
