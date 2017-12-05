package com.wiatec.boblive.service;

import com.wiatec.boblive.orm.dao.AdImageDao;
import com.wiatec.boblive.orm.pojo.ImageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 19/07/2017.
 */
@Service
public class AdImageService {

    @Resource
    private AdImageDao adImageDao;

    @Transactional(readOnly = true)
    public List<ImageInfo> selectAll(){
        return adImageDao.selectAll();
    }
}
