package com.wiatec.boblive.voucher;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherMasterTest {

    private final Logger logger = LoggerFactory.getLogger(VoucherMasterTest.class);

    @Test
    public void getRefreshToken() throws Exception {
        String refreshToken = VoucherMaster.getRefreshToken();
        logger.debug(refreshToken);
    }

    @Test
    public void getAccessToken() throws Exception {
        String access = VoucherMaster.getAccessToken();
        logger.debug(access);
    }

    @Test
    public void pay() throws Exception {
        VoucherInfo voucherInfo = VoucherMaster.pay("185160964538",
                "1231232345111123", 200);
        logger.debug(voucherInfo.toString());
    }

    @Test
    public void confirm() throws Exception {
        boolean re = VoucherMaster.confirm(VoucherMaster.pay("175132101096",
                "1231232345112", 200));
        logger.debug(re+"");
    }


}