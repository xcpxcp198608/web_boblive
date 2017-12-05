package com.wiatec.boblive.web.auth;

import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import com.wiatec.boblive.service.auth.Authorization1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by patrick on 25/08/2017.
 */
@Controller
@RequestMapping(value = "/auth1")
public class Auth1 extends BaseController {

    @Resource
    private Authorization1Service authorization1Service;

    @RequestMapping(value = "/activate")
    public @ResponseBody ResultInfo activate(HttpServletRequest request,
                                             @ModelAttribute AuthorizationInfo authorizationInfo,
                                             String manager){
        validateRequest(request);
        return authorization1Service.activateOrDeactivate(request, authorizationInfo, manager, true);
    }

    @RequestMapping(value = "/deactivate")
    public @ResponseBody ResultInfo deactivate(HttpServletRequest request,
                                             @ModelAttribute AuthorizationInfo authorizationInfo,
                                             String manager){
        System.out.println(authorizationInfo);
        validateRequest(request);
        return authorization1Service.activateOrDeactivate(request, authorizationInfo, manager,false);
    }

    @RequestMapping(value = "/update")
    public @ResponseBody ResultInfo<AuthorizationInfo> updateLevel(HttpServletRequest request,
                                               @ModelAttribute AuthorizationInfo authorizationInfo,
                                               int days){
        System.out.println(authorizationInfo);
        validateRequest(request);
        return authorization1Service.updateLevel(request, authorizationInfo, days);
    }

    @RequestMapping(value = "/updateTemporary")
    public @ResponseBody ResultInfo<AuthorizationInfo> updateTemporary(HttpServletRequest request,
                                                         @ModelAttribute AuthorizationInfo authorizationInfo){
        System.out.println(authorizationInfo);
        validateRequest(request);
        return authorization1Service.updateTemporary(request, authorizationInfo);
    }
}
