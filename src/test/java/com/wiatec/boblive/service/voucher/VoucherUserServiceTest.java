package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class VoucherUserServiceTest {

    @Autowired
    private VoucherUserService voucherUserService;

    @Test
    public void activate() {
        VoucherUserInfo voucherUserInfo = new VoucherUserInfo();
        voucherUserInfo.setMac("23423432");
        voucherUserInfo.setVoucherId("23423432");
        voucherUserInfo.setDays(11);
        voucherUserInfo.setPrice(100F);
        ResultInfo resultInfo = voucherUserService.activate(voucherUserInfo);
        System.out.println(resultInfo);
    }

    @Test
    public void validate() {
    }
}