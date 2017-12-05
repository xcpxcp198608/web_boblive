package com.wiatec.boblive.service;

import com.wiatec.boblive.common.utils.AESUtil;
import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.dao.ChannelCZDao;
import com.wiatec.boblive.orm.dao.ChannelDao;
import com.wiatec.boblive.orm.dao.ChannelSKDao;
import com.wiatec.boblive.orm.pojo.ChannelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * channel service
 */
@Service
public class ChannelService extends BaseService<ChannelInfo>{

    @Resource
    private ChannelSKDao channelSKDao;
    @Resource
    private ChannelCZDao channelCZDao;
    @Resource
    private ChannelDao channelDao;

//    //invoke channelDao query data by country
//    @Transactional(readOnly = true)
//    public ResultInfo selectByCountry(String language, String country, String token){
//        List<ChannelInfo> channelInfoList;
//        if("sk".equals(language)){
//            channelInfoList = channelSKDao.selectByCountry(country);
//        }else if("cs".equals(language)){
//            channelInfoList = channelCZDao.selectByCountry(country);
//        }else{
//            channelInfoList = channelSKDao.selectByCountry(country);
//        }
//
//        for(ChannelInfo channelInfo : channelInfoList){
//            channelInfo.setUrl(AESUtil.encrypt(channelInfo.getUrl(), AESUtil.KEY));
//        }
//        return setListResult(token, channelInfoList);
//    }

    @Transactional(readOnly = true)
    public ResultInfo selectByCountry(String language, String country, String token){
        List<ChannelInfo> channelInfoList;
        Map<String, String> map = new HashMap<>();
        map.put("country", country);
        map.put("language", language);
        channelInfoList = channelDao.selectByCountry(map);
        for(ChannelInfo channelInfo : channelInfoList){
            channelInfo.setUrl(AESUtil.encrypt(channelInfo.getUrl(), AESUtil.KEY));
        }
        return setListResult(token, channelInfoList);
    }
}
