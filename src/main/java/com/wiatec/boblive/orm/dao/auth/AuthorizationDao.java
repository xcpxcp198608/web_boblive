package com.wiatec.boblive.orm.dao.auth;

import com.wiatec.boblive.orm.pojo.auth.AuthorizationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * data operation interface of authorization table
 */
@Repository
public interface AuthorizationDao {

    /**
     * bath insert key
     * @param map required: key , sales, dealer, leader
     */
    void insertBathKey(Map<String, Object> map);

    /**
     * key exists?
     * @param authorizationInfo key
     * @return exists: 1, not exists: other
     */
    int countKey(AuthorizationInfo authorizationInfo);

    /**
     * select active status
     * @param authorizationInfo required: key
     * @return active: 1
     */
    int selectActiveByKey(AuthorizationInfo authorizationInfo);

    /**
     * update auth mac, active, activeTime by key
     * @param authorizationInfo required: key, mac
     */
    void updateActive(AuthorizationInfo authorizationInfo);

    /**
     * validate auth by key and mac
     * @param authorizationInfo required: key , mac
     * @return count: 1 -> YES, other -> NO
     */
    int countOne(AuthorizationInfo authorizationInfo);

    /**
     * select one auto information by key
     * @param authorizationInfo required: key
     * @return
     */
    AuthorizationInfo selectOneById(AuthorizationInfo authorizationInfo);
    AuthorizationInfo selectOneByKey(AuthorizationInfo authorizationInfo);


    List<AuthorizationInfo> selectActiveByLeader(String leader);
    List<AuthorizationInfo> selectActiveBySales(String sales);


    List<AuthorizationInfo> selectActive(AuthorizationInfo authorizationInfo);
    List<AuthorizationInfo> selectNegative(AuthorizationInfo authorizationInfo);

    List<AuthorizationInfo> selectNegativeByLeader(String leader);

    void activate(AuthorizationInfo authorizationInfo);
    void deactivate(AuthorizationInfo authorizationInfo);
    void updateLevel(AuthorizationInfo authorizationInfo);
    void updateTemporary(AuthorizationInfo authorizationInfo);

}
