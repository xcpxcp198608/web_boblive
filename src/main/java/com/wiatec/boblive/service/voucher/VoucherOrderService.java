package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.orm.dao.voucher.VoucherOrderDao;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.voucher.VoucherMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoucherOrderService {

    private Logger logger = LoggerFactory.getLogger(VoucherOrderService.class);

    @Resource
    private VoucherOrderDao voucherOrderDao;

    public List<VoucherOrderInfo> selectAll(){
        return voucherOrderDao.selectAll();
    }

    public ResultInfo verifyTransaction(String transactionId){
        boolean b;
        try {
            b = VoucherMaster.verifyTransaction(transactionId);
        } catch (Exception e) {
            throw new XException(ResultMaster.error(1009, e.getMessage()));
        }
        if(b) {
            voucherOrderDao.updateStatusToApproved(transactionId);
            return ResultMaster.success();
        }else{
            throw new XException(ResultMaster.error(1009, "verify failure"));
        }
    }

}
