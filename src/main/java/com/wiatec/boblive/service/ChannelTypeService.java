package com.wiatec.boblive.service;

import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.dao.ChannelTypeDao;
import com.wiatec.boblive.orm.pojo.ChannelTypeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * channel type service
 */
@Service
public class ChannelTypeService extends BaseService<ChannelTypeInfo> {

    @Resource
    private ChannelTypeDao channelTypeDao;

    // invoke channelTypeDao query all channel type information
    @Transactional(readOnly = true)
    public ResultInfo selectByType(String type, String token){
        return setListResult(token, channelTypeDao.selectByType(type));
    }
}
