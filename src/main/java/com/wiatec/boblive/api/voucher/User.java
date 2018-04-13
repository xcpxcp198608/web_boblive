package com.wiatec.boblive.api.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.listener.SessionListener;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserCategoryInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import com.wiatec.boblive.service.voucher.VoucherUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author patrick
 */
@RestController
@RequestMapping(value = "/voucher/user")
public class User {

    @Resource
    private VoucherUserService voucherUserService;

    /**
     * user activate by voucher
     * @param voucherUserInfo   required: voucher id , mac , month, category
     * @return                  {@link ResultInfo}
     */
    @PostMapping(value = "/activate")
    public ResultInfo activate(VoucherUserInfo voucherUserInfo,
                               @RequestParam(required = false, defaultValue = "en_US") String lang){
        return voucherUserService.activate(voucherUserInfo, lang);
    }

    /**
     * validate user
     * @param mac  mac
     * @param session HttpSession
     * @return
     */
    @GetMapping(value = "/validate/{mac}")
    public ResultInfo validate(@PathVariable String mac, HttpSession session, @RequestParam(required = false, defaultValue = "en_US") String lang){
        return voucherUserService.validate(mac, session, lang);
    }

}
