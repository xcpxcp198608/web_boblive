package com.wiatec.boblive.orm.pojo.voucher;

import com.wiatec.boblive.voucher.VoucherInfo;

import java.util.Date;

/**
 * @author patrick
 */
public class VoucherOrderInfo {

    public static final int STATUS_CONSUME = 0;
    public static final int STATUS_CONFIRM = 1;
    public static final int STATUS_ERROR = -1;
    public static final String CURRENCY_CZK = "CZK";

    private int id;
    private String transactionId;
    private String voucherId;
    private String mac;
    private float amount;
    private float price;
    private String currency;
    private String auth;
    private int status;
    private Date createTime;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
                ", currency='" + currency + '\'' +
                ", auth='" + auth + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public static VoucherOrderInfo createForSaveOrder(VoucherUserInfo voucherUserInfo, VoucherInfo voucherInfo){
        VoucherOrderInfo voucherOrderInfo = new VoucherOrderInfo();
        voucherOrderInfo.setMac(voucherUserInfo.getMac());
        voucherOrderInfo.setPrice(voucherUserInfo.getPrice());

        voucherOrderInfo.setVoucherId(voucherInfo.getVoucher()+"");
        voucherOrderInfo.setTransactionId(voucherInfo.getTransaction());
        voucherOrderInfo.setAmount(voucherInfo.getAmount());
        voucherOrderInfo.setCurrency(voucherInfo.getCurrency());
        voucherOrderInfo.setAuth(voucherInfo.getAuth());
        voucherOrderInfo.setStatus(STATUS_CONSUME);
        return voucherOrderInfo;
    }

}
