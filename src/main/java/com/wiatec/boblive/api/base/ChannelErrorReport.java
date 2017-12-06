package com.wiatec.boblive.api.base;

import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.orm.pojo.ChannelErrorReportInfo;
import com.wiatec.boblive.service.ChannelErrorReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 08/08/2017.
 */
@Controller
@RequestMapping(value = "/report")
public class ChannelErrorReport {

    @Resource
    private ChannelErrorReportService channelErrorReportService;

    @RequestMapping(value = "/")
    public String getAll(Model model){
        List<ChannelErrorReportInfo> channelReportInfoList = channelErrorReportService.selectAll();
        model.addAttribute("channelReportInfoList", channelReportInfoList);
        return "channelreport/show";
    }

    @RequestMapping(value = "/send")
    public @ResponseBody
    ResultInfo insert(@ModelAttribute ChannelErrorReportInfo channelErrorReportInfo){
        return channelErrorReportService.insertOne(channelErrorReportInfo);
    }

}
