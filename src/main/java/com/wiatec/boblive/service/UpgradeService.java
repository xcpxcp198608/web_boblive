package com.wiatec.boblive.service;

import com.wiatec.boblive.orm.dao.UpgradeDao;
import com.wiatec.boblive.orm.pojo.UpdateInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * update service
 * @author patrick
 */
@Service
public class UpgradeService {

    @Resource
    private UpgradeDao upgradeDao;

    public UpdateInfo selectOne(){
        return upgradeDao.selectOne();
    }
}
