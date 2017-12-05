package com.wiatec.boblive.orm.dao;

import com.wiatec.boblive.orm.pojo.ChannelTypeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * data operation interface of channel type table
 */
@Repository
public interface ChannelTypeDao {

    //select all channel type information from the table of 'channel type'
    List<ChannelTypeInfo> selectByType(String type);
}
