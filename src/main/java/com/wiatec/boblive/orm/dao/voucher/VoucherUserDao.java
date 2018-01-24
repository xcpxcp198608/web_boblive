package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherAdminInfo;
import com.wiatec.boblive.orm.pojo.voucher.VoucherUserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author patrick
 */
@Repository
public interface VoucherUserDao {

    int countOneByMac(VoucherUserInfo voucherUserInfo);
    void insertOne(VoucherUserInfo voucherUserInfo);
    void updateByMac(VoucherUserInfo voucherUserInfo);

    VoucherUserInfo selectOneByMac(String mac);
}
