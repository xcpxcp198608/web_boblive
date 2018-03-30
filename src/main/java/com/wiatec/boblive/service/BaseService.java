package com.wiatec.boblive.service;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.AESUtil;
import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.listener.SessionListener;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * basic service
 */
public class BaseService<T> {

    /**
     * set result data
     * @param list query result from dao operation
     * @return
     */
    protected ResultInfo<T> setListResult(String token , List<T> list){
        String tokenAfterDecrypt = AESUtil.decrypt(token,AESUtil.KEY);
        if(!tokenAfterDecrypt.startsWith("5c:41:e7")){
            throw new XException(EnumResult.ERROR_TOKEN_INCORRECT);
        }
        if(list == null || list.size() <= 0){
            throw new XException(EnumResult.ERROR_NO_FOUND);
        }else{
            return ResultMaster.success(list);
        }
    }

    protected String getCurrentAdminName(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute(SessionListener.KEY_USER_NAME);
        if(TextUtil.isEmpty(username)){
            throw new XException(EnumResult.ERROR_UNAUTH);
        }
        return username;
    }

}
