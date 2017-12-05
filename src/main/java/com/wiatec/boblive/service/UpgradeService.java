package com.wiatec.boblive.service;

import com.wiatec.boblive.orm.dao.UpgradeDao;
import com.wiatec.boblive.orm.pojo.UpdateInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * update service
 */
@Service
public class UpgradeService {

    @Resource
    private UpgradeDao upgradeDao;

    /**
     * invoke updateDao query update information
     * @return
     */
    @Transactional(readOnly = true)
    public UpdateInfo selectOne(){
        return upgradeDao.selectOne();
    }
}
