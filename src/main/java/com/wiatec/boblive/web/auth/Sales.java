package com.wiatec.boblive.web.auth;

import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthDealerInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthSalesInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import com.wiatec.boblive.service.auth.AuthSalesService;
import com.wiatec.boblive.service.auth.Authorization1Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuchengpeng on 24/08/2017.
 */
@Controller
@RequestMapping(value = "/sales")
public class Sales extends BaseController {

    @Resource
    private AuthSalesService authSalesService;

    @Resource
    private Authorization1Service authorization1Service;

    @RequestMapping(value = "/")
    public @ResponseBody List<AuthSalesInfo> list(HttpServletRequest request, @ModelAttribute AuthSalesInfo authSalesInfo){
        validateRequest(request);
        System.out.println(authSalesInfo);
        return authSalesService.listAll(request , authSalesInfo);
    }

    @RequestMapping(value = "/create")
    public @ResponseBody ResultInfo<AuthSalesInfo> create(HttpServletRequest request,
                                                          @ModelAttribute AuthSalesInfo authSalesInfo){
        validateRequest(request);
        System.out.println(authSalesInfo);
        return authSalesService.create(request, authSalesInfo);
    }

    @RequestMapping(value = "/update")
    public @ResponseBody ResultInfo<AuthSalesInfo> update(HttpServletRequest request ,
                                                          @ModelAttribute AuthSalesInfo authSalesInfo){
        validateRequest(request);
        System.out.println(authSalesInfo);
        return authSalesService.updatePassword(request, authSalesInfo);
    }

    /**
     * show all active auth under current leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/active")
    public String activeL(HttpServletRequest request, Model model, @ModelAttribute AuthorizationInfo authorizationInfo,
                          @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        List<AuthorizationInfo> authorizationInfoList =  authorization1Service.listActive(request, authorizationInfo);
        model.addAttribute("authorizationInfoList", authorizationInfoList);
        List<AuthSalesInfo> authSalesInfoList =  authSalesService.listAll(request, new AuthSalesInfo());
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        return "manager/sales/active";
    }

    /**
     * show all negative auth under current leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/negative")
    public String negativeL(HttpServletRequest request, Model model,
                            @ModelAttribute AuthorizationInfo authorizationInfo){
        validateRequest(request);
        List<AuthorizationInfo> authorizationInfoList =  authorization1Service.listNegative(request, authorizationInfo);
        model.addAttribute("authorizationInfoList", authorizationInfoList);
        return "manager/sales/negative";
    }

}
