package com.wiatec.boblive.voucher;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherMasterTest {

    private Logger logger = LoggerFactory.getLogger(VoucherMasterTest.class);


    @Test
    public void pay() throws Exception {
        VoucherInfo voucherInfo = VoucherMaster.pay("102692089224", "12312323451");
        logger.debug(voucherInfo.toString());
    }

    @Test
    public void validate() throws Exception {
        boolean re = VoucherMaster.confirm("102616489468", 1.0f);
        logger.debug(re+"");
    }

    @Test
    public void verifyTransaction() throws Exception {
        boolean b = VoucherMaster.verifyTransaction("fa6c7df178cad32d");
        logger.debug(b+"");
    }

}