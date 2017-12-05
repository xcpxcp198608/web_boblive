package com.wiatec.boblive.orm.pojo.auth;

/**
 * Created by xuchengpeng on 23/06/2017.
 */
public class AuthorizationInfo {

    private int id;
    private String key;
    private String mac;
    private short active;
    private String activeDate;
    private long activeTime;
    private String sales;
    private String dealer;
    private String leader;
    private short level;
    private String memberDate;
    private long memberTime;
    private boolean effective = true; //是否可用，设置成false时客户端弹出不可用提示
    private boolean temporary; //设置为true时客户端拥有临时频道权限
    private boolean online;

    public AuthorizationInfo() {
    }

    public AuthorizationInfo(String sales) {
        this.sales = sales;
    }


    public AuthorizationInfo(String sales, String dealer) {
        this.sales = sales;
        this.dealer = dealer;
    }

    public AuthorizationInfo(String sales, String dealer, String leader) {
        this.sales = sales;
        this.dealer = dealer;
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
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

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    public long getMemberTime() {
        return memberTime;
    }

    public void setMemberTime(long memberTime) {
        this.memberTime = memberTime;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "AuthorizationInfo{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", mac='" + mac + '\'' +
                ", active=" + active +
                ", activeDate='" + activeDate + '\'' +
                ", activeTime=" + activeTime +
                ", sales='" + sales + '\'' +
                ", dealer='" + dealer + '\'' +
                ", leader='" + leader + '\'' +
                ", level=" + level +
                ", memberDate='" + memberDate + '\'' +
                ", memberTime=" + memberTime +
                ", effective=" + effective +
                ", temporary=" + temporary +
                ", online=" + online +
                '}';
    }
}
