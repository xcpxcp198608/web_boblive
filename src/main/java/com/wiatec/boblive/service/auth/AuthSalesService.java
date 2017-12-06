package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TextUtil;
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
        authSalesInfo = setAuthSalesInfo(request, authSalesInfo);
        try{
            if(TextUtil.isEmpty(authSalesInfo.getUserName())){
                throw new XException(EnumResult.ERROR_USERNAME_EMPTY);
            }
            if(TextUtil.isEmpty(authSalesInfo.getPassword())){
                throw new XException(EnumResult.ERROR_PASSWORD_EMPTY);
            }
            if(authSalesInfo.getUserName().length() < 5){
                throw new XException(EnumResult.ERROR_USERNAME_FORMAT);
            }
            if(authSalesInfo.getPassword().length() < 6){
                throw new XException(EnumResult.ERROR_PASSWORD_FORMAT);
            }
            authSalesInfo.setUserName("S"+authSalesInfo.getUserName());
            if(authSalesDao.countUserName(authSalesInfo.getUserName()) == 1){
                throw new XException(EnumResult.ERROR_USERNAME_EXISTS);
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
            return ResultMaster.success(authSalesDao.selectOne(authSalesInfo.getUserName()));
        }catch (Exception e){
            throw new XException(ResultMaster.error(1009, "create failure"));
        }
    }


    @Transactional
    public ResultInfo<AuthSalesInfo> updatePassword(HttpServletRequest request, AuthSalesInfo authSalesInfo){
        try{
            if(TextUtil.isEmpty(authSalesInfo.getUserName())){
                throw new XException(EnumResult.ERROR_USERNAME_EMPTY);
            }
            if(TextUtil.isEmpty(authSalesInfo.getPassword())){
                throw new XException(EnumResult.ERROR_PASSWORD_EMPTY);
            }
            authSalesDao.updatePassword(authSalesInfo);
            return ResultMaster.success();
        }catch (Exception e){
            throw new XException(ResultMaster.error(1009, "update failure"));
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
