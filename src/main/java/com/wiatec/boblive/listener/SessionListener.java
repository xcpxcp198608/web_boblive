package com.wiatec.boblive.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * monitor session
 */
public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener {

    private Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public static Map<String ,HttpSession> keySessionMap = new HashMap<>();
    public static Map<String ,HttpSession> voucherSessionMap = new HashMap<>();
    public static Map<String ,HttpSession> userSessionMap = new HashMap<>();
    public static final String KEY = "key";
    public static final String KEY_VOUCHER = "voucherKey";
    public static final String KEY_USER_NAME = "userName";

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug(httpSessionEvent.getSession().getId()+" created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug(httpSessionEvent.getSession().getId()+" destroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        if(KEY.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            HttpSession httpSession = keySessionMap.remove(userName);
            if(httpSession != null){
                httpSession.removeAttribute(KEY);
            }
            keySessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
        if(KEY_VOUCHER.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            HttpSession httpSession = voucherSessionMap.remove(userName);
            if(httpSession != null){
                httpSession.removeAttribute(KEY_VOUCHER);
            }
            voucherSessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
        if(KEY_USER_NAME.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            HttpSession httpSession = userSessionMap.remove(userName);
            if(httpSession != null){
                httpSession.removeAttribute(KEY_USER_NAME);
            }
            userSessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        if(KEY.equals(httpSessionBindingEvent.getName())){
            try {
                String userName = (String) httpSessionBindingEvent.getValue();
                keySessionMap.remove(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(KEY_VOUCHER.equals(httpSessionBindingEvent.getName())){
            try {
                String userName = (String) httpSessionBindingEvent.getValue();
                voucherSessionMap.remove(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(KEY_USER_NAME.equals(httpSessionBindingEvent.getName())){
            try {
                String userName = (String) httpSessionBindingEvent.getValue();
                userSessionMap.remove(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        if(KEY.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            keySessionMap.remove(userName);
            keySessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
        if(KEY_VOUCHER.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            voucherSessionMap.remove(userName);
            voucherSessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
        if(KEY_USER_NAME.equals(httpSessionBindingEvent.getName())){
            String userName = (String) httpSessionBindingEvent.getValue();
            userSessionMap.remove(userName);
            userSessionMap.put(userName, httpSessionBindingEvent.getSession());
        }
    }

    public static HttpSession getKeySession (String userName){
        return keySessionMap.get(userName);
    }

    public static HttpSession getVoucherSessionMap (String userName){
        return voucherSessionMap.get(userName);
    }

    public static HttpSession getUserSession (String userName){
        return userSessionMap.get(userName);
    }
}
