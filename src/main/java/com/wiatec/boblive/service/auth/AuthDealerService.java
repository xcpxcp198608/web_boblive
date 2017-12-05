package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.entity.ResultInfo;
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
    public ResultInfo<AuthDealerInfo>  create(HttpServletRequest request, AuthDealerInfo authDealerInfo){
        ResultInfo<AuthDealerInfo> resultInfo = new ResultInfo<> ();
        try {
            String leader = getCurrentUserName(request);
            authDealerInfo.setLeader(leader);
            if(TextUtil.isEmpty(authDealerInfo.getUserName())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("user name error");
                return resultInfo;
            }
            if(TextUtil.isEmpty(authDealerInfo.getPassword())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("password error");
                return resultInfo;
            }
            if(authDealerInfo.getUserName().length() < 5){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("dealer name length < 5");
                return resultInfo;
            }
            if(authDealerInfo.getPassword().length() < 6){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("password length< 6");
                return resultInfo;
            }
            authDealerInfo.setUserName("D"+authDealerInfo.getUserName());
            if(authDealerDao.countUserName(authDealerInfo.getUserName()) == 1){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("dealer name already exists");
                return resultInfo;
            }
            authDealerDao.insertOne(authDealerInfo);
            AuthDealerInfo authDealerInfo1 = authDealerDao.selectOne(authDealerInfo.getUserName());
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("create successfully");
            resultInfo.setObj(authDealerInfo1);
            return resultInfo;
        }catch (Exception e) {
            resultInfo.setCode(ResultInfo.CODE_SERVER_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_SERVER_ERROR);
            resultInfo.setMessage("create failure");
            return resultInfo;
        }
    }

    @Transactional
    public ResultInfo<AuthDealerInfo> updatePassword(HttpServletRequest request, AuthDealerInfo authDealerInfo){
        ResultInfo<AuthDealerInfo> resultInfo = new ResultInfo<> ();
        try {
            String leader = getCurrentUserName(request);
            authDealerInfo.setLeader(leader);
            if(TextUtil.isEmpty(authDealerInfo.getUserName())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("Username error");
                return resultInfo;
            }
            if(TextUtil.isEmpty(authDealerInfo.getPassword())){
                resultInfo.setCode(ResultInfo.CODE_INVALID);
                resultInfo.setStatus(ResultInfo.STATUS_INVALID);
                resultInfo.setMessage("password format error");
                return resultInfo;
            }
            authDealerDao.updatePassword(authDealerInfo);
            AuthDealerInfo authDealerInfo1 = authDealerDao.selectOne(authDealerInfo.getUserName());
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setMessage("update successfully");
            resultInfo.setObj(authDealerInfo1);
            return resultInfo;
        }catch (Exception e) {
            resultInfo.setCode(ResultInfo.CODE_SERVER_ERROR);
            resultInfo.setStatus(ResultInfo.STATUS_SERVER_ERROR);
            resultInfo.setMessage("update failure");
            return resultInfo;
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
