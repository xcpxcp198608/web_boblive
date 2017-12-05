package com.wiatec.boblive.api.base;

import com.wiatec.boblive.orm.pojo.ImageInfo;
import com.wiatec.boblive.service.AdImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 19/07/2017.
 */
@Controller
@RequestMapping(value = "/adimage")
public class AdImage {

    @Resource
    private AdImageService adImageService;

    @RequestMapping(value = "/")
    public @ResponseBody List<ImageInfo> getAll(){
        return adImageService.selectAll();
    }
}
