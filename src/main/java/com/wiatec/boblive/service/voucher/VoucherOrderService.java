package com.wiatec.boblive.service.voucher;

import com.wiatec.boblive.orm.dao.voucher.VoucherOrderDao;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}
