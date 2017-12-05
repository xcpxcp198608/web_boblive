package com.wiatec.boblive.api.base;

import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.service.ChannelTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * channel type controller
 */
@Controller
@RequestMapping(value = "/channel_type")
public class ChannelType {

    @Resource
    private ChannelTypeService channelTypeService;

    /**
     * return channel type information to client
     * @param token
     * @return
     */
    @RequestMapping(value = "/{type}/{token}")
    public @ResponseBody ResultInfo getByType(@PathVariable String type,
                                              @PathVariable("token") String token){
        return channelTypeService.selectByType(type, token);
    }
}
