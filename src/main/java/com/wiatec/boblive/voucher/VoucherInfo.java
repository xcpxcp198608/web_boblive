package com.wiatec.boblive.voucher;

/**
 * @author patrick
 */
public class VoucherInfo {
    private long voucher;
    private long amount;
    private String currency;
    private String transaction;
    private String auth;

    public long getVoucher() {
        return voucher;
    }

    public void setVoucher(long voucher) {
        this.voucher = voucher;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "VoucherInfo{" +
                "voucher=" + voucher +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", transaction='" + transaction + '\'' +
                ", auth='" + auth + '\'' +
                '}';
    }
}