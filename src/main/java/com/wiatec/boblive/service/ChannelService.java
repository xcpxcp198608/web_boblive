package com.wiatec.boblive.service;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.utils.AESUtil;
import com.wiatec.boblive.orm.dao.ChannelDao;
import com.wiatec.boblive.orm.pojo.ChannelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * channel service
 * @author patrick
 */
@Service
public class ChannelService extends BaseService<ChannelInfo>{

    @Resource
    private ChannelDao channelDao;

    public ResultInfo selectByCountry(String language, String country, String token){
        List<ChannelInfo> channelInfoList;
        if("VOUCHER".equals(country)){
            channelInfoList = channelDao.selectVoucherChannel();
        }else {
            Map<String, String> map = new HashMap<>(2);
            map.put("country", country);
            map.put("language", language);
            channelInfoList = channelDao.selectByCountry(map);
        }
        for(ChannelInfo channelInfo : channelInfoList){
            channelInfo.setUrl(AESUtil.encrypt(channelInfo.getUrl(), AESUtil.KEY));
        }
        return setListResult(token, channelInfoList);
    }
}
