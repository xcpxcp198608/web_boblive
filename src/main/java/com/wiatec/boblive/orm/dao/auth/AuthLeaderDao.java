package com.wiatec.boblive.orm.dao.auth;

import com.wiatec.boblive.orm.pojo.auth.AuthLeaderInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by xuchengpeng on 22/08/2017.
 */
@Repository
public interface AuthLeaderDao {
    int countOne(AuthLeaderInfo authLeaderInfo);
    AuthLeaderInfo selectOne(String userName);
}
