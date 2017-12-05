package com.wiatec.boblive.api.base;

import com.wiatec.boblive.orm.pojo.UpdateInfo;
import com.wiatec.boblive.service.UpgradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * controller update
 */
@Controller
@RequestMapping(value = "/upgrade")
public class Upgrade {

    @Resource
    private UpgradeService upgradeService;

    /**
     * return update information to client
     * @return
     */
    @RequestMapping(value = "/")
    public @ResponseBody  UpdateInfo get(){
        return upgradeService.selectOne();
    }
}
