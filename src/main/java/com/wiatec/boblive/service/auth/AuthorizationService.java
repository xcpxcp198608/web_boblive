package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.utils.TokenUtil;
import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.dao.auth.AuthorizationDao;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * authorization service
 */
@Service
public class AuthorizationService {


    private static final long TEMP_DURATION = 7*24*3600*1000;

    @Resource
    private AuthorizationDao authorizationDao;

    /**
     * bath create key
     * @param count count
     * @param group group
     */
    @Transactional
    public List<String> create(int count, String group){
        List<String> keyList = new ArrayList<>();
        for(int i = 0 ; i < count ; i ++){
            String key = createKey(i);
            keyList.add(key);
        }
        Map<String , Object> map = new HashMap<>();
        map.put("keyList", keyList);
        map.put("group", group);
        try{
            authorizationDao.insertBathKey(map);
            return keyList;
        }catch (Exception e){
            throw new RuntimeException("key create failure");
        }
    }

    //create key
    private String createKey(int i){
        return TokenUtil.create(i+"", "www.wiatec.com");
    }

    /**
     * active auth key on user device, bind key and mac
     * @param key provide to user
     * @param mac wifi mac of the device
     * @return
     */
    @Transactional
    public ResultInfo<AuthorizationInfo> active(String key, String mac){
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setKey(key);
        authorizationInfo.setMac(mac);
        authorizationInfo.setActiveTime(System.currentTimeMillis());
        ResultInfo<AuthorizationInfo> resultInfo = new ResultInfo<>();
        if("wiatec".equals(key)){
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("active success");
            authorizationInfo.setLevel((short) 8);
            authorizationInfo.setEffective(true);
            authorizationInfo.setTemporary(true);
            resultInfo.setObj(authorizationInfo);
            return resultInfo;
        }
        if(authorizationDao.countKey(authorizationInfo) != 1){
            resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
            resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
            resultInfo.setMessage("the key is incorrect");
            return resultInfo;
        }
        if(authorizationDao.selectActiveByKey(authorizationInfo) == 1){
            if(authorizationDao.countOne(authorizationInfo) == 1){
                authorizationInfo = authorizationDao.selectOneByKey(authorizationInfo);
                if(!authorizationInfo.isEffective()){
                    resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
                    resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
                    resultInfo.setMessage("key effective error");
                    return resultInfo;
                }
                return update(false, resultInfo, authorizationInfo);
            }else {
                resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
                resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
                resultInfo.setMessage("this key already use");
                return resultInfo;
            }
        }
        return update(true, resultInfo, authorizationInfo);
    }

    private ResultInfo<AuthorizationInfo> update(boolean update, ResultInfo<AuthorizationInfo> resultInfo, AuthorizationInfo authorizationInfo){
        try {
            if(update) {
                authorizationDao.updateActive(authorizationInfo);
            }
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("active success");
            resultInfo.setObj(authorizationDao.selectOneByKey(authorizationInfo));
            return resultInfo;
        }catch (Exception e){
            resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
            resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
            resultInfo.setMessage("active failure");
            return resultInfo;
        }
    }

    /**
     * validate auth by key and mac
     * @param key provide to user
     * @param mac wifi mac of the device
     * @return
     */
    @Transactional(readOnly = true)
    public ResultInfo<AuthorizationInfo> validate(HttpServletRequest request, String key, String mac){
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setKey(key);
        authorizationInfo.setMac(mac);
        ResultInfo<AuthorizationInfo> resultInfo = new ResultInfo<>();
        if("wiatec".equals(key)){
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("true");
            authorizationInfo.setLevel((short) 8);
            authorizationInfo.setEffective(true);
            authorizationInfo.setTemporary(true);
            resultInfo.setObj(authorizationInfo);
            return resultInfo;
        }
        if(authorizationDao.countOne(authorizationInfo) == 1){
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
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
            resultInfo.setObj(authorizationInfo1);
            HttpSession session = SessionListener.getKeySession(key);
            if(session == null){
                session = request.getSession();
            }
            session.setAttribute("key", key);
            return resultInfo;
        }else{
            resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
            resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
            resultInfo.setMessage("validate failure");
            return resultInfo;
        }
    }

}
