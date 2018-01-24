package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class VoucherOrderDaoTest {

    @Autowired
    private VoucherOrderDao voucherOrderDao;

    @Test
    public void selectAll() {
        List<VoucherOrderInfo> voucherOrderInfoList = voucherOrderDao.selectAll();
        System.out.println(voucherOrderInfoList);
    }

    @Test
    public void insertOne() {
        VoucherOrderInfo voucherOrderInfo = new VoucherOrderInfo();
        voucherOrderInfo.setMac("4234");
        voucherOrderInfo.setPrice(20F);
        voucherOrderInfo.setAmount(20F);
        voucherOrderInfo.setCurrency(VoucherOrderInfo.CURRENCY_CZK);
        voucherOrderInfo.setVoucherId("sdfsd");
        voucherOrderInfo.setTransactionId("sdfdsfsdf");
        voucherOrderInfo.setAuth("23423");
        int one = voucherOrderDao.insertOne(voucherOrderInfo);
        System.out.println(one);

    }

    @Test
    public void updateStatusToConfirm() {
        int i = voucherOrderDao.updateStatusToConfirm("4234");
        assertEquals(1, i);
        System.out.println(i);
    }

    @Test
    public void selectByMacAndVoucherId() {
        VoucherOrderInfo voucherOrderInfo = voucherOrderDao.selectByMacAndVoucherId("4234", "sdfsd");
        System.out.println(voucherOrderInfo);
    }
}