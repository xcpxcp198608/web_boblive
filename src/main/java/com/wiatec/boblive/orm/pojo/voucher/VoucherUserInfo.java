package com.wiatec.boblive.orm.pojo.voucher;

/**
 * @author patrick
 */
public class VoucherUserInfo {

    private int id;
    private String mac;
    private String voucherId;
    private int level;
    private int days;
    private float price;
    private String activateTime;
    private String expiresTime;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(String activateTime) {
        this.activateTime = activateTime;
    }

    public String getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(String expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VoucherUserInfo{" +
                "id=" + id +
                ", mac='" + mac + '\'' +
                ", voucherId='" + voucherId + '\'' +
                ", level=" + level +
                ", days=" + days +
                ", price=" + price +
                ", activateTime='" + activateTime + '\'' +
                ", expiresTime='" + expiresTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
