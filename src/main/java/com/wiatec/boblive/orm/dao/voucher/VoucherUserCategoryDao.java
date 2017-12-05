package com.wiatec.boblive.orm.dao.voucher;

import com.wiatec.boblive.orm.pojo.voucher.VoucherUserCategoryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherUserCategoryDao {

    List<VoucherUserCategoryInfo> selectAll();
    VoucherUserCategoryInfo selectOneByCategory(String category);
}
