package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherOrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author patrick
 */
@Repository
public interface VoucherOrderDao {

    /**
     * check the voucher is already consume
     * @param mac mac
     * @param voucherId voucher id
     * @return VoucherOrderInfo
     */
    VoucherOrderInfo selectByMacAndVoucherId(@Param("mac") String mac, @Param("voucherId") String voucherId);

    /**
     * insert a voucher order information, the status is consume(0) now
     * @param voucherOrderInfo VoucherOrderInfo
     * @return 1 -> success 0 -> failure
     */
    int insertOne(VoucherOrderInfo voucherOrderInfo);

    /**
     * update a voucher order status to confirm(1)
     * @param mac mac address
     * @return 1 -> success 0 -> failure
     */
    int updateStatusToConfirm(String mac);

    /**
     * query all voucher order information
     * @return all VoucherOrderInfo
     */
    List<VoucherOrderInfo> selectAll();
}
