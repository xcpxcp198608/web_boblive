package com.wiatec.boblive.api.auth;

import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.service.auth.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * authorization controller
 */
@Controller
@RequestMapping(value = "/auth")
public class Authorization {

    @Resource
    private AuthorizationService authorizationService;

    /**
     *  client active key
     * @param key key
     * @param mac mac of the device
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.POST)
    public @ResponseBody ResultInfo active(@RequestParam String key, @RequestParam String mac){
        return authorizationService.active(key, mac);
    }

    /**
     * client validate key and mac
     * @param key key
     * @param mac mac of device
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody ResultInfo validate(HttpServletRequest request, @RequestParam String key, @RequestParam String mac){
        return authorizationService.validate(request, key, mac);
    }

}
