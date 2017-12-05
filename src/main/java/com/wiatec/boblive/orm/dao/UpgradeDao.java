package com.wiatec.boblive.orm.dao;

import com.wiatec.boblive.orm.pojo.UpdateInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * data operation interface of update table
 */
@Repository
public interface UpgradeDao {
    //query the first row from the table of update
    UpdateInfo selectOne();
}
