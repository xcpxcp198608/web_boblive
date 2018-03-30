package com.wiatec.boblive.voucher;


import com.google.gson.Gson;
import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.result.EnumResult;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import com.wiatec.boblive.common.utils.TextUtil;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author patrick
 */
public class VoucherMaster {

    private static final Logger logger = LoggerFactory.getLogger(VoucherMaster.class);

    private static final String BASE_URL = "https://ws.voucher4u.eu/v1/";
    private static final String VOUCHER_CONSUME = BASE_URL + "vouchers/consume";
    private static final String VOUCHER_CONFIRM = BASE_URL + "transactions/confirm";


    public static VoucherInfo pay(String voucherId, String transactionId, float price) {
        Response response = HttpsMaster.post(VOUCHER_CONSUME)
                .headers("Authorization", "Bearer "+ VoucherTokenMaster.getAccessToken())
                .headers("Content-Type", "application/json")
                .json("{\"voucher\":"+voucherId+"\n" +
                        ", \"transaction\":\""+transactionId+"\"}")
                .execute1();
        String result = null;
        try {
            result = response.body().string();
        } catch (IOException e){
            logger.error("IOException", e);
        }
        if(result == null){
            throw new XException(1001, "network communication error");
        }
        logger.debug(result);
        if(response.code() != HttpURLConnection.HTTP_OK) {
            if(response.code() == 401){
                // token out expiration
                try {
                    VoucherTokenMaster.accessToken();
                } catch (Exception e) {
                    logger.error("Exception:", e);
                }
                pay(voucherId, transactionId, price);
            }else {
                JSONObject jsonObject = new JSONObject(result);
                String error = jsonObject.getString("error");
                throw new XException(ResultMaster.error(1003, error));
            }
        }
        VoucherInfo voucherInfo;
        try {
            voucherInfo = new Gson().fromJson(result, VoucherInfo.class);
        }catch (Exception e){
            logger.error("Exception:", e);
            throw new XException(ResultMaster.error(1005, "result parse error"));
        }
        voucherInfo.setTransaction(transactionId);
        if(voucherInfo.getAmount() / 100 < price){
            throw new XException(ResultMaster.error(1006, "amount no enough"));
        }
        logger.debug(voucherInfo.toString());
        return voucherInfo;
    }

    public static boolean confirm(VoucherInfo voucherInfo){
        Response response = HttpsMaster.post(VOUCHER_CONFIRM)
                .headers("Authorization", "Bearer "+ VoucherTokenMaster.getAccessToken())
                .headers("Content-Type", "application/json")
                .json("{\"voucher\":"+voucherInfo.getVoucher() +
                        ",\"transaction\":\"" + voucherInfo.getTransaction() +
                        "\",\"auth\":\" " + voucherInfo.getAuth() + " \"}")
                .execute1();
        return response.code() == 200;
    }

}
