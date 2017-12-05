package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.orm.dao.voucher.VoucherAdminDao;
import com.wiatec.boblive.orm.pojo.voucher.VoucherAdminInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VoucherAdminService {

    private Logger logger = LoggerFactory.getLogger(VoucherAdminService.class);

    @Resource
    private VoucherAdminDao voucherAdminDao;

    public ResultInfo signIn(VoucherAdminInfo voucherAdminInfo){
        try {
            int permission = voucherAdminDao.selectPermission(voucherAdminInfo);
            if(permission <= 0){
                return ResultMaster.error(EnumResult.ERROR_USERNAME_PASSWORD_NO_MATCH);
            }
            return ResultMaster.success();
        }catch (Exception e){
            logger.debug(e.getMessage());
            return ResultMaster.error(EnumResult.ERROR_USERNAME_PASSWORD_NO_MATCH);
        }
    }
}
