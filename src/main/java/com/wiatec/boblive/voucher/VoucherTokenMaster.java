package com.wiatec.boblive.voucher;

import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.utils.TextUtil;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author patrick
 */

@Component
public class VoucherTokenMaster {

    private static final Logger logger = LoggerFactory.getLogger(VoucherTokenMaster.class);
    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    private static final String BASE_URL = "https://ws.voucher4u.eu/v1/";
    private static final String TOKEN_CREATE = BASE_URL + "token/create";
    private static final String TOKEN_REFRESH = BASE_URL + "token/refresh";
    private static final String TOKEN_ACCESS = BASE_URL + "token/issue";

    public static void main (String [] args){
        getAccessToken();
    }

    @Scheduled(cron = "0 0 2 1-31 1-12 1-7")
    public static void createToken(){
        String s = HttpsMaster.post(TOKEN_CREATE).execute();
        logger.debug("create result: {}", s);
        if(TextUtil.isEmpty(s)){
            return;
        }
        JSONObject jsonObject = new JSONObject(s);
        String token = jsonObject.getString("refresh");
        logger.debug("create token: {}", token);
        tokenMap.put("token", token);
    }

    @Scheduled(cron = "0 0 3 1-31 1-12 1-7")
    public static void refreshToken() throws Exception{
        String token = tokenMap.get("token");
        if(TextUtil.isEmpty(token)){
            createToken();
            token = tokenMap.get("token");
        }
        Response response = HttpsMaster.post(TOKEN_REFRESH)
                .headers("Authorization", "Bearer " + token)
                .execute1();
        if(response.code() == 401){
            createToken();
            refreshToken();
        }else {
            String result = response.body().string();
            JSONObject j = new JSONObject(result);
            String refreshToken = j.getString("refresh");
            tokenMap.put("refreshToken", refreshToken);
            logger.debug("refresh token: {}", refreshToken);
        }
    }

    @Scheduled(fixedRate = 1800000)
    public static void accessToken() throws Exception{
        logger.debug("access token start...");
        String refreshToken = tokenMap.get("refreshToken");
        if(TextUtil.isEmpty(refreshToken)){
            refreshToken();
            refreshToken = tokenMap.get("refreshToken");
        }
        Response response = HttpsMaster.post(TOKEN_ACCESS)
                .headers("Authorization", "Bearer " + refreshToken)
                .execute1();
        // token out expiration
        if(response.code() == 401){
            refreshToken();
            accessToken();
        }else {
            String result = response.body().string();
            JSONObject j = new JSONObject(result);
            String accessToken = j.getString("access");
            tokenMap.put("accessToken", accessToken);
            logger.debug("access token: {}", accessToken);
        }
    }


    public static String getAccessToken(){
        try {
            String token = tokenMap.get("accessToken");
            if(TextUtil.isEmpty(token)){
                accessToken();
                return getAccessToken();
            }
            return token;
        }catch (Exception e){
            logger.error("token exception", e);
        }
        return "";
    }

}
