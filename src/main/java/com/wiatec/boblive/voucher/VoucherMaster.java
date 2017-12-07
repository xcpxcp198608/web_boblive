package com.wiatec.boblive.voucher;


import com.google.gson.Gson;
import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class VoucherMaster {

    private static Logger logger = LoggerFactory.getLogger(VoucherMaster.class);

    private static final String BASE_URL = "https://pay.ws.test.ecoupon.pro";
    private static final String KIOSK_ID = "14";

    public static VoucherInfo pay(String voucherId, String transactionId) throws Exception {
        String url  = BASE_URL + "/voucher/" + voucherId + "/pay/kiosk/" + KIOSK_ID + "/lang/en/trn/" + transactionId;
        String result = HttpsMaster.get(url).execute();
        VoucherInfo voucherInfo = new Gson().fromJson(result, VoucherInfo.class);
        if(voucherInfo == null){
            throw new XException(ResultMaster.error(10003, "json parse error"));
        }
        if(voucherInfo.getResult() != 0){
            throw new XException(ResultMaster.error(10003, voucherInfo.getError()));
        }
        logger.debug(voucherInfo.toString());
        return voucherInfo;
    }

    public static boolean confirm(String voucherId, float price) throws Exception{
        String url = BASE_URL + "/voucher/" + voucherId + "/confirm/0";
        logger.debug(url);
        String result = HttpsMaster.post(url).execute();
        logger.debug(result);
        JSONObject jsonObject = new JSONObject(result);
        int code = jsonObject.getInt("result");
        String message = jsonObject.getString("error");
        if(code != 0){
            throw new XException(ResultMaster.error(10003, message));
        }
        JSONObject voucher = jsonObject.getJSONObject("voucher");
        long amount = voucher.getLong("amount") / 100;
        logger.debug(amount+"");
        return amount >= price;
    }

    public static boolean verifyTransaction(String transactionId) throws Exception{
        String url  = BASE_URL + "/transaction/"+ transactionId + "/info";
        String result = HttpsMaster.get(url).execute();
        if(result.contains("\":\"{\"")){
            result = result.replace("\":\"{\"", "\":{\"");
        }
        if(result.contains("}}\"}}")){
            result = result.replace("}}\"}}", "}}}}");
        }
        logger.debug(result);
        JSONObject jsonObject = new JSONObject(result);
        int code = jsonObject.getInt("result");
        String message = jsonObject.getString("error");
        if(code != 0){
            throw new XException(ResultMaster.error(10003, message));
        }
        JSONObject transaction = jsonObject.getJSONObject("transaction");
        String trnId = transaction.getString("id");
        if(!Objects.equals(trnId, transactionId)){
            return false;
        }
        JSONObject detail = transaction.getJSONObject("detail");
        int resultCode = detail.getInt("result");
        return resultCode == 0;
    }

}
