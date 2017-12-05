package com.wiatec.boblive.orm.dao.auth;

import com.wiatec.boblive.orm.pojo.auth.AuthSalesInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuchengpeng on 22/08/2017.
 */
@Repository
public interface AuthSalesDao {
    int countOne(AuthSalesInfo authSalesInfo);
    void insertOne(AuthSalesInfo authSalesInfo);
    void updatePassword(AuthSalesInfo authSalesInfo);
    void deleteOne(AuthSalesInfo authSalesInfo);
    AuthSalesInfo selectOne(String userName);
    List<AuthSalesInfo> select(AuthSalesInfo authSalesInfo);
    String getDealer(String sales);
    int countUserName(String userName); // check username is exists?
}
