package com.wiatec.boblive.web.auth;

import com.wiatec.boblive.service.auth.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuchengpeng on 24/08/2017.
 */
@Controller
@RequestMapping(value = "/manager")
public class Manager extends BaseController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public String login(@RequestParam String userName, @RequestParam String password,
                        HttpServletRequest request, HttpServletResponse response){
        validateRequest(request);
        return loginService.login(userName, password, request, response);
    }


}
