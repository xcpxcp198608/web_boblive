package com.wiatec.boblive.web.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.service.voucher.VoucherOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "/verify/{transactionId}")
    @ResponseBody
    public ResultInfo verify(@PathVariable String transactionId){
        return voucherOrderService.verifyTransaction(transactionId);
    }
}
