package com.wiatec.boblive.web.auth;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * leader controller
 */
@Controller
@RequestMapping(value = "/leader")
public class Leader extends BaseController {

    @Resource
    private AuthDealerService authDealerService;

    @Resource
    private AuthSalesService authSalesService;

    @Resource
    private Authorization1Service authorization1Service;


    /**
     * show dealer list in leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/dealer")
    public String dealerL(HttpServletRequest request, Model model, @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, authDealerInfo);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        return "manager/leader/dealer";
    }

    /**
     * show sales list by leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sales")
    public String salesL(HttpServletRequest request, Model model, @ModelAttribute AuthSalesInfo authSalesInfo,
                         @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        List<AuthSalesInfo> authSalesInfoList = authSalesService.listAll(request, authSalesInfo);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, authDealerInfo);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        return "manager/leader/sales";
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
        System.out.println(authorizationInfo);
        List<AuthorizationInfo> authorizationInfoList =  authorization1Service.listActive(request, authorizationInfo);
        model.addAttribute("authorizationInfoList", authorizationInfoList);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, authDealerInfo);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        model.addAttribute("authorizationInfo",authorizationInfo);
        return "manager/leader/active";
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
        return "manager/leader/negative";
    }

    /**
     * go to auth_create page, and show all dealer and sale list in <select></select> of jsp
     * under current leader account
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/gc_auth")
    public String goCreate(HttpServletRequest request, Model model, @ModelAttribute AuthDealerInfo authDealerInfo){
        validateRequest(request);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, authDealerInfo);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        List<AuthSalesInfo> authSalesInfoList = authSalesService.listAll(request, new AuthSalesInfo());
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        return "manager/leader/auth_create";
    }

    /**
     * create16 auth by current leader account
     * @param request
     * @param model
     * @param dealer dealer
     * @param sales sales
     * @param count create16 count
     * @return show create16 result after create16 successfully
     */
    @RequestMapping(value = "/create_auth")
    public String create(HttpServletRequest request, Model model, String dealer, String sales, int count,
                         @ModelAttribute AuthorizationInfo authorizationInfo){
        validateRequest(request);
        List<String> keyList = authorization1Service.createAuth(request, authorizationInfo, count);
        List<AuthDealerInfo> authDealerInfoList =  authDealerService.listDealer(request, new AuthDealerInfo());
        List<AuthSalesInfo> authSalesInfoList = authSalesService.listAll(request, new AuthSalesInfo());
        model.addAttribute("keyList", keyList);
        model.addAttribute("authDealerInfoList", authDealerInfoList);
        model.addAttribute("authSalesInfoList", authSalesInfoList);
        model.addAttribute("sales", sales);
        model.addAttribute("dealer", dealer);
        return "manager/leader/auth_create";
    }


}
