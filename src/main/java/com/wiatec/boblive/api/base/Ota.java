package com.wiatec.boblive.api.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/ota")
public class Ota {

    @RequestMapping(value = "/")
    public @ResponseBody String get(HttpServletRequest request){
        String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        return "url="+path+"/Resource/xml/ota_update.xml";
    }
}
