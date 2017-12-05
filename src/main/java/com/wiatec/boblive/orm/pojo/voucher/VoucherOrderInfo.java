package com.wiatec.boblive.orm.pojo.voucher;

public class VoucherOrderInfo {

    public static final String STATUS_COMPLETED = "completed";
    public static final String STATUS_APPROVED = "approved";

    private int id;
    private String transactionId;
    private String voucherId;
    private String mac;
    private float amount;
    private float price;
    private String status;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VoucherOrderInfo{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", voucherId='" + voucherId + '\'' +
                ", mac='" + mac + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
