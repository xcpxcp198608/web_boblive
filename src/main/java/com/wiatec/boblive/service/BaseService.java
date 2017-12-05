package com.wiatec.boblive.service;

import com.wiatec.boblive.common.utils.AESUtil;
import com.wiatec.boblive.entity.ResultInfo;
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
            ResultInfo<T> resultInfo = new ResultInfo<>();
            resultInfo.setCode(ResultInfo.CODE_UNAUTHORIZED);
            resultInfo.setStatus(ResultInfo.STATUS_UNAUTHORIZED);
            resultInfo.setMessage(tokenAfterDecrypt);
            return resultInfo;
        }
        ResultInfo<T> resultInfo = new ResultInfo<>();
        if(list == null || list.size() <= 0){
            resultInfo.setCode(ResultInfo.CODE_NO_FOUND);
            resultInfo.setStatus(ResultInfo.STATUS_NO_FOUND);
        }else{
            resultInfo.setCode(ResultInfo.CODE_OK);
            resultInfo.setStatus(ResultInfo.STATUS_OK);
            resultInfo.setData(list);
        }
        return resultInfo;
    }

    private String getSessionIdFromRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String sessionId = cookies[0].getValue();
        System.out.println(sessionId);
        return sessionId;
    }

    private HttpSession getSession(HttpServletRequest request, String sessionId){
        HttpSession session = SessionListener.getUserSession(sessionId);
        if(session == null){
            session = request.getSession();
        }
        return session;
    }

    private void setUserNameInSession(HttpSession session, String userName){
        session.setAttribute("userName", userName);
    }

    private String getUserNameFormSession(HttpSession session){
        return session.getAttribute("userName").toString();
    }

    private String getRealSessionId(HttpServletRequest request){
        return getSession(request, getSessionIdFromRequest(request)).getId();
    }

    protected void setUserName(HttpServletRequest request, String userName){
        String sessionId= getSessionIdFromRequest(request);
        HttpSession session = getSession(request, sessionId);
        setUserNameInSession(session, userName);
    }

    protected void setCookie(HttpServletRequest request, HttpServletResponse response){
        String sessionId = getRealSessionId(request);
        response.addCookie(new Cookie("JSESSIONID",sessionId));
    }

    protected String getCurrentUserName(HttpServletRequest request){
        String sessionId= getSessionIdFromRequest(request);
        HttpSession session = getSession(request, sessionId);
        return getUserNameFormSession(session);
    }

}
