package com.wiatec.boblive.service;

import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultInfo;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TextUtil;
import com.wiatec.boblive.orm.dao.ChannelErrorReportDao;
import com.wiatec.boblive.orm.pojo.ChannelErrorReportInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author xuchengpeng
 * @date 08/08/2017
 */
@Service
public class ChannelErrorReportService {

    @Resource
    private ChannelErrorReportDao channelErrorReportDao;


    public List<ChannelErrorReportInfo> selectAll(){
        return channelErrorReportDao.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo insertOne(ChannelErrorReportInfo channelErrorReportInfo){
        if(TextUtil.isEmpty(channelErrorReportInfo.getUserName())){
            throw new XException(EnumResult.ERROR_USERNAME_NOT_EXISTS);
        }
        channelErrorReportDao.insertOne(channelErrorReportInfo);
        return ResultMaster.success();
    }
}
