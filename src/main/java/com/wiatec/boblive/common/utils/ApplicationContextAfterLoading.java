package com.wiatec.boblive.common.utils;

import com.wiatec.boblive.voucher.VoucherMaster;
import com.wiatec.boblive.voucher.VoucherTokenMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * @author patrick
 */
public class ApplicationContextAfterLoading implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationContextAfterLoading.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            logger.debug("application loading completed");
            try {
                VoucherTokenMaster.accessToken();
            } catch (Exception e) {
                logger.error("token exception", e);
            }
        }
    }

}
