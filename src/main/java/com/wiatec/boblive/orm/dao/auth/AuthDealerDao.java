package com.wiatec.boblive.orm.dao.auth;

import com.wiatec.boblive.orm.pojo.auth.AuthDealerInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuchengpeng on 22/08/2017.
 */
@Repository
public interface AuthDealerDao {
    int countOne(AuthDealerInfo authDealerInfo); //validate login
    void insertOne(AuthDealerInfo authDealerInfo); //create dealer
    void updatePassword(AuthDealerInfo authDealerInfo); //update password
    void deleteOne(AuthDealerInfo authDealerInfo);
    AuthDealerInfo selectOne(String userName);
    List<AuthDealerInfo> select(AuthDealerInfo authDealerInfo);
    String getLeader(String dealer);
    int countUserName(String userName);// check username is exists
}
