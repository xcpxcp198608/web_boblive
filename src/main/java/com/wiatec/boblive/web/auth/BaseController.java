package com.wiatec.boblive.web.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuchengpeng on 24/08/2017.
 */
public class BaseController {

    protected void validateRequest(HttpServletRequest request){
        String ref = request.getHeader("referer");
        if(!ref.contains("/boblive/")){
            throw new RuntimeException("login info error");
        }
    }
}
