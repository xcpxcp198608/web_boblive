package com.wiatec.boblive.orm.dao;

import com.wiatec.boblive.orm.pojo.ImageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuchengpeng on 19/07/2017.
 */
@Repository
public interface AdImageDao {

    List<ImageInfo> selectAll();
}
