package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherAdminInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherOrderDao {

    List<VoucherOrderInfo> selectAll();
    void insertOne(VoucherOrderInfo voucherOrderInfo);
    void updateStatusToApproved(String transactionId);
}
