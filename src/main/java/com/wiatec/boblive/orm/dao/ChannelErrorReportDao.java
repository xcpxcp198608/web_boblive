package com.wiatec.boblive.orm.dao;

import com.wiatec.boblive.orm.pojo.ChannelErrorReportInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuchengpeng on 08/08/2017.
 */
@Repository
public interface ChannelErrorReportDao {

    List<ChannelErrorReportInfo> selectAll();
    void insertOne(ChannelErrorReportInfo channelErrorReportInfo);
}
