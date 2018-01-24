package com.wiatec.boblive.web.auth;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthDealerInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthSalesInfo;
import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import com.wiatec.boblive.service.auth.AuthDealerService;
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
 *
 * @author xuchengpeng
 * @date 24/08/2017
 */
@Controller
@RequestMapping(value = "/dealer")
public class Dealer extends BaseController {

    @Resource
    private AuthDealerService authDealerService;

    @Resource
    private AuthSalesService authSalesService;

    @Resource
    private Authorization1Service authorization1Service;

    @RequestMapping(value = "/create")
    @ResponseBody
    public ResultInfo create(HttpServletRequest request, @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        System.out.println(authDealerInfo);
        return authDealerService.create(request, authDealerInfo);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo update(HttpServletRequest request,
                                                           @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        System.out.println(authDealerInfo);
        return authDealerService.updatePassword(request, authDealerInfo);
    }

    @RequestMapping(value = "/sales")
    public String listSales(HttpServletRequest request, @ModelAttribute AuthSalesInfo authSalesInfo,
                                         Model model){
        List<AuthSalesInfo> authSalesInfoList =  authSalesService.listAll(request, authSalesInfo);
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        return "manager/dealer/sales";
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
        return "manager/dealer/active";
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
        return "manager/dealer/negative";
    }

    /**
     * go to auth_create page, and show all dealer and sale list in <select></select> of jsp
     * under current leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/gc_auth")
    public String goCreate(HttpServletRequest request, Model model){
        validateRequest(request);
        List<AuthSalesInfo> authSalesInfoList = authSalesService.listAll(request, new AuthSalesInfo());
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        System.out.println(authSalesInfoList);
        return "manager/dealer/auth_create";
    }

    /**
     * create16 auth by current leader account
     * @param request
     * @param model
     * @param sales sales
     * @param count create16 count
     * @return show create16 result after create16 successfully
     */
    @RequestMapping(value = "/create_auth")
    public String create(HttpServletRequest request, Model model, String sales, int count,
                         @ModelAttribute AuthorizationInfo authorizationInfo){
        validateRequest(request);
        List<String> keyList = authorization1Service.createAuth(request, authorizationInfo, count);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, new AuthDealerInfo());
        List<AuthSalesInfo> authSalesInfoList = authSalesService.listAll(request, new AuthSalesInfo());
        model.addAttribute("keyList", keyList);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        model.addAttribute("sales", sales);
        return "manager/dealer/auth_create";
    }

}
