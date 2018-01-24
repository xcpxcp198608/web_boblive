package com.wiatec.boblive.service;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.orm.dao.ChannelTypeDao;
import com.wiatec.boblive.orm.pojo.ChannelTypeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * channel type service
 * @author patrick
 */
@Service
public class ChannelTypeService extends BaseService<ChannelTypeInfo> {

    @Resource
    private ChannelTypeDao channelTypeDao;

    public ResultInfo selectByType(String type, String token){
        return setListResult(token, channelTypeDao.selectByType(type));
    }
}
