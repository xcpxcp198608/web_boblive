package com.wiatec.boblive.api.base;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.service.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * channel controller
 */
@Controller
@RequestMapping(value = "/channel")
public class Channel {

    @Resource
    private ChannelService channelService;

    /**
     * return channel information by country to client
     * @param country query parameter: country
     * @param token
     * @return
     */
    @RequestMapping(value = "/{language}/{country}/{token}")
    public @ResponseBody
    ResultInfo getByCountry(@PathVariable("language") String language,
                            @PathVariable("country") String country,
                            @PathVariable("token") String token){
        return channelService.selectByCountry(language, country, token);
    }

}
