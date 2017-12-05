package com.wiatec.boblive.api.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserCategoryInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import com.wiatec.boblive.service.voucher.VoucherUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/voucher/user")
public class User {

    @Resource
    private VoucherUserService voucherUserService;

    @GetMapping(value = "/category")
    public ResultInfo<VoucherUserCategoryInfo> getCategory(){
        return voucherUserService.listCategory();
    }

    /**
     * user activate by voucher
     * @param voucherUserInfo   required: voucher id , mac , month, category
     * @return                  {@link ResultInfo}
     */
    @PostMapping(value = "/activate")
    public ResultInfo activate(VoucherUserInfo voucherUserInfo){
        return voucherUserService.activate(voucherUserInfo);
    }

    @GetMapping(value = "/validate/{mac}")
    public ResultInfo validate(@PathVariable String mac, HttpSession session){
        return voucherUserService.validate(mac, session);
    }

}
