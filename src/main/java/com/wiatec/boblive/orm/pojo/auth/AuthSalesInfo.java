package com.wiatec.boblive.orm.pojo.auth;

/**
 * Created by xuchengpeng on 22/08/2017.
 */
public class AuthSalesInfo {

    private int id;
    private String userName;
    private String password;
    private String dealer;
    private String leader;
    private String registerTime;
    private String updateTime;

    public AuthSalesInfo() {
    }

    public AuthSalesInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthSalesInfo(String dealer) {
        this.dealer = dealer;
    }

    public AuthSalesInfo(String userName, String password, String dealer, String leader) {
        this.userName = userName;
        this.password = password;
        this.dealer = dealer;
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AuthSalesInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dealer='" + dealer + '\'' +
                ", leader='" + leader + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
