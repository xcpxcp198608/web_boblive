package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.dao.auth.AuthDealerDao;
import com.wiatec.boblive.orm.dao.auth.AuthSalesDao;
import com.wiatec.boblive.orm.pojo.auth.AuthSalesInfo;
import com.wiatec.boblive.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuchengpeng on 23/08/2017.
 */
@Service
public class AuthSalesService extends BaseService {

    @Resource
    private AuthSalesDao authSalesDao;

    @Resource
    private AuthDealerDao authDealerDao;

    @Transactional(readOnly = true)
    public List<AuthSalesInfo> listAll(HttpServletRequest request, AuthSalesInfo authSalesInfo){
        try {
            String manager = getCurrentUserName(request);
            if(manager.startsWith("L")){
                authSalesInfo.setLeader(manager);
            }
            if(manager.startsWith("D")){
                authSalesInfo.setDealer(manager);
                authSalesInfo.setLeader(authDealerDao.getLeader(manager));
            }
        }catch (Exception e){
            return null;
        }
        return authSalesDao.select(authSalesInfo);
    }

    public ResultInfo<AuthSalesInfo> create(HttpServletRequest request, AuthSalesInfo authSalesInfo){
        ResultInfo<AuthSalesInfo> resultInfo = new ResultInfo<>();
        authSalesInfo = setAuthSalesInfo(request, authSalesInfo);
        try{
            if(TextUtil.isEmpty(authSalesInfo.getUserName())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales name error");
                return resultInfo;
            }
            if(TextUtil.isEmpty(authSalesInfo.getPassword())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales password error");
                return resultInfo;
            }
            if(authSalesInfo.getUserName().length() < 5){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales name length < 5");
                return resultInfo;
            }
            if(authSalesInfo.getPassword().length() < 6){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("password length < 6 ");
                return resultInfo;
            }
            authSalesInfo.setUserName("S"+authSalesInfo.getUserName());
            if(authSalesDao.countUserName(authSalesInfo.getUserName()) == 1){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales name already exists");
                return resultInfo;
            }
            String manager = getCurrentUserName(request);
            if(!TextUtil.isEmpty(manager)) {
                if (manager.startsWith("D")) {
                    authSalesInfo.setDealer(manager);
                    authSalesInfo.setLeader(authDealerDao.getLeader(manager));
                }
                if (manager.startsWith("L")) {
                    authSalesInfo.setLeader(manager);
                }
            }
            authSalesDao.insertOne(authSalesInfo);
            AuthSalesInfo authSalesInfo1 = authSalesDao.selectOne(authSalesInfo.getUserName());
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("create successfully");
            resultInfo.setObj(authSalesInfo1);
            return resultInfo;
        }catch (Exception e){
            resultInfo.setCode(ResultInfo.CODE_SERVER_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_SERVER_ERROR);
            resultInfo.setMessage("create failure");
            return resultInfo;
        }
    }


    @Transactional
    public ResultInfo<AuthSalesInfo> updatePassword(HttpServletRequest request, AuthSalesInfo authSalesInfo){
        ResultInfo<AuthSalesInfo> resultInfo = new ResultInfo<>();
        try{
            if(TextUtil.isEmpty(authSalesInfo.getUserName())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales name format error");
                return resultInfo;
            }
            if(TextUtil.isEmpty(authSalesInfo.getPassword())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("sales password format error");
                return resultInfo;
            }
            authSalesDao.updatePassword(authSalesInfo);
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

    private AuthSalesInfo setAuthSalesInfo(HttpServletRequest request, AuthSalesInfo authSalesInfo){
        String manager = getCurrentUserName(request);
        if(manager.startsWith("L")){
            authSalesInfo.setLeader(manager);
        }else if(manager.startsWith("D")){
            authSalesInfo.setDealer(manager);
            authSalesInfo.setLeader(authDealerDao.getLeader(manager));
        }
        return authSalesInfo;
    }

}
