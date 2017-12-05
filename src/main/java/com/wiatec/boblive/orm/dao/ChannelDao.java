package com.wiatec.boblive.orm.dao;

import com.wiatec.boblive.orm.pojo.ChannelInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * data operation interface of channel table
 */
@Repository
public interface ChannelDao {

    List<ChannelInfo> selectByCountry(Map<String, String> map);
    List<ChannelInfo> selectBasic();
}
