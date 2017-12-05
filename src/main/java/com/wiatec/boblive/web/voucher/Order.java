package com.wiatec.boblive.web.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.service.voucher.VoucherOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/voucher/order")
public class Order {

    @Resource
    private VoucherOrderService voucherOrderService;

    @RequestMapping(value = "/")
    public String show(Model model){
        List<VoucherOrderInfo> voucherOrderInfoList = voucherOrderService.selectAll();
        model.addAttribute("voucherOrderInfoList", voucherOrderInfoList);
        return "voucher/order";
    }
}
