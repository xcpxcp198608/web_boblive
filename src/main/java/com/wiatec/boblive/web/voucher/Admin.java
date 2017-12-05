package com.wiatec.boblive.web.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.pojo.voucher.VoucherAdminInfo;
import com.wiatec.boblive.service.voucher.VoucherAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/voucher")
public class Admin {
    @Resource
    private VoucherAdminService voucherAdminService;

    @RequestMapping(value = "/")
    public String index(){
        return "voucher/index";
    }


    @RequestMapping(value = "/signin")
    @ResponseBody
    public ResultInfo adminSignIn(VoucherAdminInfo voucherAdminInfo){
        return voucherAdminService.signIn(voucherAdminInfo);
    }

    @GetMapping(value = "/online")
    public int online(){
        return SessionListener.voucherSessionMap.size();
    }
}
