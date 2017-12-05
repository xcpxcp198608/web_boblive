package com.wiatec.boblive.service;

import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.entity.ResultInfo;
import com.wiatec.boblive.orm.dao.ChannelErrorReportDao;
import com.wiatec.boblive.orm.pojo.ChannelErrorReportInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 08/08/2017.
 */
@Service
public class ChannelErrorReportService {

    @Resource
    private ChannelErrorReportDao channelErrorReportDao;

    @Transactional(readOnly = true)
    public List<ChannelErrorReportInfo> selectAll(){
        return channelErrorReportDao.selectAll();
    }

    @Transactional
    public ResultInfo insertOne(ChannelErrorReportInfo channelErrorReportInfo){
        if(TextUtil.isEmpty(channelErrorReportInfo.getUserName())){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(ResultInfo.CODE_INVALID);
            resultInfo.setStatus(ResultInfo.STATUS_INVALID);
            resultInfo.setMessage("user name can not empty");
            return resultInfo;
        }
        channelErrorReportDao.insertOne(channelErrorReportInfo);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(ResultInfo.CODE_OK);
        resultInfo.setStatus(ResultInfo.STATUS_OK);
        resultInfo.setMessage("send successfully");
        return resultInfo;
    }
}
