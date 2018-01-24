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

import java.util.Objects;

/**
 * @author patrick
 */
public class VoucherMaster {

    private static final Logger logger = LoggerFactory.getLogger(VoucherMaster.class);

    private static final String BASE_URL = "https://ws.test.voucher4u.eu/v1/";

    public static String getRefreshToken() throws Exception{
        String url = BASE_URL + "token/refresh";
        String refreshToken = "";
        String result = HttpsMaster.post(url)
                .headers("Authorization", "Bearer "+ VoucherTokenMaster.getToken()).execute();
        JSONObject j = new JSONObject(result);
        refreshToken = j.getString("refresh");
        return refreshToken;
    }

    public static String getAccessToken() throws Exception{
        String url = BASE_URL + "token/issue";
        String accessToken = "";
        String result = HttpsMaster.post(url)
                .headers("Authorization", "Bearer "+ getRefreshToken()).execute();
        JSONObject j = new JSONObject(result);
        accessToken = j.getString("access");
        return accessToken;
    }

    public static VoucherInfo pay(String voucherId, String transactionId, float price) throws Exception {
        String url  = BASE_URL + "vouchers/consume";
        Response response = HttpsMaster.post(url)
                .headers("Authorization", "Bearer "+ getAccessToken())
                .headers("Content-Type", "application/json")
                .json("{\"voucher\":"+voucherId+"\n" +
                        ", \"transaction\":\""+transactionId+"\"}")
                .execute1();
        String result = response.body().string();
        logger.debug(result);
        if(response.code() != 200) {
            JSONObject jsonObject = new JSONObject(result);
            String error = jsonObject.getString("error");
            throw new XException(ResultMaster.error(10003, error));
        }
        if(TextUtil.isEmpty(result)){
            throw new XException(ResultMaster.error(10004, "network communication error"));
        }
        VoucherInfo voucherInfo;
        try {
            voucherInfo = new Gson().fromJson(result, VoucherInfo.class);
        }catch (Exception e){
            logger.error("Exception: ", e);
            throw new XException(ResultMaster.error(10005, "json parse error"));
        }
        voucherInfo.setTransaction(transactionId);
        if(voucherInfo.getAmount() / 100 < price){
            throw new XException(ResultMaster.error(10006, "amount no enough"));
        }
        logger.debug(voucherInfo.toString());
        return voucherInfo;
    }

    public static boolean confirm(VoucherInfo voucherInfo) throws Exception{
        String url = BASE_URL + "transactions/confirm";
        Response response = HttpsMaster.post(url)
                .headers("Authorization", "Bearer "+ getAccessToken())
                .headers("Content-Type", "application/json")
                .json("{\"voucher\":"+voucherInfo.getVoucher() +
                        ",\"transaction\":\"" + voucherInfo.getTransaction() +
                        "\",\"auth\":\" " + voucherInfo.getAuth() + " \"}")
                .execute1();
        return response.code() == 200;
    }

    public static boolean queryTransaction(String transactionId) throws Exception{
        String url  = BASE_URL + "transactions/log";

        return true;
    }

}
