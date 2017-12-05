package com.wiatec.boblive.orm.pojo.auth;

/**
 * Created by xuchengpeng on 22/08/2017.
 */
public class AuthLeaderInfo {

    private int id;
    private String userName;
    private String password;
    private String registerTime;
    private String updateTime;

    public AuthLeaderInfo() {
    }

    public AuthLeaderInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
        return "AuthLeaderInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
