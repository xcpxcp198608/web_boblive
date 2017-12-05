package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherAdminInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherAdminDao {

    int selectPermission(VoucherAdminInfo voucherAdminInfo);
}
