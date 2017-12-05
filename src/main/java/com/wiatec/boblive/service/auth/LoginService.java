package com.wiatec.boblive.service.auth;

import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.orm.dao.auth.AuthDealerDao;
import com.wiatec.boblive.orm.dao.auth.AuthLeaderDao;
import com.wiatec.boblive.orm.dao.auth.AuthSalesDao;
import com.wiatec.boblive.orm.pojo.auth.AuthDealerInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthLeaderInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthSalesInfo;
import com.wiatec.boblive.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuchengpeng on 23/08/2017.
 */
@Service
public class LoginService extends BaseService {

    @Resource
    private AuthLeaderDao authLeaderDao;

    @Resource
    private AuthDealerDao authDealerDao;

    @Resource
    private AuthSalesDao authSalesDao;

    @Transactional(readOnly = true)
    public String login(String userName, String password, HttpServletRequest request,
                        HttpServletResponse response){
        if(TextUtil.isEmpty(userName)){
            throw new RuntimeException("user name empty");
        }
        if(TextUtil.isEmpty(password)){
            throw new RuntimeException("password empty");
        }
        if(userName.startsWith("L")){
            boolean flag = authLeaderDao.countOne(new AuthLeaderInfo(userName, password)) == 1;
            if(flag){
                setCookie(request, response);
                setUserName(request, userName);
                return "redirect:/leader/dealer";
            }
        }else if(userName.startsWith("D")){
            boolean flag = authDealerDao.countOne(new AuthDealerInfo(userName, password)) == 1;
            if(flag){
                setCookie(request, response);
                setUserName(request, userName);
                return "redirect:/dealer/sales";
            }
        }else if(userName.startsWith("S")){
            boolean flag = authSalesDao.countOne(new AuthSalesInfo(userName, password)) == 1;
            if(flag){
                setCookie(request, response);
                setUserName(request, userName);
                return "redirect:/sales/active";
            }
        }else{
            throw new RuntimeException("user name error");
        }
        throw new RuntimeException("user name or password error");
    }
}
