package com.wiatec.boblive.voucher;


import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoucherMaster {

    private static Logger logger = LoggerFactory.getLogger(VoucherMaster.class);

    private static final String BASE_URL = "https://pay.ws.test.ecoupon.pro";
    private static final String KIOSK_ID = "14";

    public static boolean pay(String voucherId, float price, String transactionId){
        String url  = BASE_URL + "/voucher/" + voucherId + "/pay/kiosk/" + KIOSK_ID + "/lang/en/trn/" + transactionId;
        String result = HttpsMaster.get(url).execute();
        JSONObject jsonObject = new JSONObject(result);
        int code = jsonObject.getInt("result");
        String message = jsonObject.getString("error");
        if(code !=0){
            throw new XException(ResultMaster.error(10003, message));
        }
        JSONObject voucher = jsonObject.getJSONObject("voucher");
        float amount = voucher.getLong("amount") / 100;
        logger.debug(amount+"");
        return amount >= price;
    }

    public static float confirm(String voucherId){
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
        long amount = voucher.getLong("amount");
        float a = amount / 100;
        logger.debug(a+"");
        return a;
    }

    public static boolean verifyTransaction(String transactionId){
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
