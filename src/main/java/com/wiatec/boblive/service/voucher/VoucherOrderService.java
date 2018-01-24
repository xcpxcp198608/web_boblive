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

/**
 * @author patrick
 */
@Service
public class VoucherOrderService {

    private Logger logger = LoggerFactory.getLogger(VoucherOrderService.class);

    @Resource
    private VoucherOrderDao voucherOrderDao;

    public List<VoucherOrderInfo> selectAll(){
        return voucherOrderDao.selectAll();
    }



}
