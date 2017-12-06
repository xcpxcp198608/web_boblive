package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.orm.dao.auth.AuthDealerDao;
import com.wiatec.boblive.orm.pojo.auth.AuthDealerInfo;
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
public class AuthDealerService extends BaseService {

    @Resource
    private AuthDealerDao authDealerDao;

    @Transactional
    public ResultInfo<AuthDealerInfo> create(HttpServletRequest request, AuthDealerInfo authDealerInfo){
        try {
            String leader = getCurrentUserName(request);
            authDealerInfo.setLeader(leader);
            if(TextUtil.isEmpty(authDealerInfo.getUserName())){
                throw new XException(EnumResult.ERROR_USERNAME_EMPTY);
            }
            if(TextUtil.isEmpty(authDealerInfo.getPassword())){
                throw new XException(EnumResult.ERROR_PASSWORD_EMPTY);
            }
            if(authDealerInfo.getUserName().length() < 5){
                throw new XException(EnumResult.ERROR_USERNAME_FORMAT);
            }
            if(authDealerInfo.getPassword().length() < 6){
                throw new XException(EnumResult.ERROR_PASSWORD_FORMAT);
            }
            authDealerInfo.setUserName("D"+authDealerInfo.getUserName());
            if(authDealerDao.countUserName(authDealerInfo.getUserName()) == 1){
                throw new XException(EnumResult.ERROR_USERNAME_EXISTS);
            }
            authDealerDao.insertOne(authDealerInfo);
            AuthDealerInfo authDealerInfo1 = authDealerDao.selectOne(authDealerInfo.getUserName());
            return ResultMaster.success(authDealerInfo1);
        }catch (Exception e) {
            throw new XException(EnumResult.ERROR_CREATE_FAILURE);
        }
    }

    @Transactional
    public ResultInfo<AuthDealerInfo> updatePassword(HttpServletRequest request, AuthDealerInfo authDealerInfo){
        ResultInfo<AuthDealerInfo> resultInfo = new ResultInfo<> ();
        try {
            String leader = getCurrentUserName(request);
            authDealerInfo.setLeader(leader);
            if(TextUtil.isEmpty(authDealerInfo.getUserName())){
                throw new XException(EnumResult.ERROR_USERNAME_EMPTY);
            }
            if(TextUtil.isEmpty(authDealerInfo.getPassword())){
                throw new XException(EnumResult.ERROR_PASSWORD_EMPTY);
            }
            authDealerDao.updatePassword(authDealerInfo);
            AuthDealerInfo authDealerInfo1 = authDealerDao.selectOne(authDealerInfo.getUserName());
            return ResultMaster.success(authDealerInfo1);
        }catch (Exception e) {
            throw new XException(EnumResult.ERROR_UPDATE_FAILURE);
        }
    }

    @Transactional(readOnly = true)
    public List<AuthDealerInfo> listDealer(HttpServletRequest request, AuthDealerInfo authDealerInfo ){
        String leader = getCurrentUserName(request);
        if(leader != null && leader.startsWith("L")){
            authDealerInfo.setLeader(leader);
        }
        return authDealerDao.select(authDealerInfo);
    }

}
