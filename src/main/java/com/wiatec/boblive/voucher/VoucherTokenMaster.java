package com.wiatec.boblive.voucher;

import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.utils.TextUtil;
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

    public static void main (String [] args){
        createToken();
    }


    @Scheduled(cron = "0 0 2 1-31 1-12 1-7")
    public static void createToken(){
        try {
            String s = HttpsMaster.post("https://ws.test.voucher4u.eu/v1/token/create").execute();
            JSONObject jsonObject = new JSONObject(s);
            String token = jsonObject.getString("refresh");
            logger.debug(token);
            tokenMap.put("token", token);
        }catch (Exception e){
            logger.debug(e.getLocalizedMessage());
        }
    }

    public static String getToken(){
        String token = tokenMap.get("token");
        if(TextUtil.isEmpty(token)){
            String s = HttpsMaster.post("https://ws.test.voucher4u.eu/v1/token/create").execute();
            logger.debug(s);
            JSONObject jsonObject = new JSONObject(s);
            token = jsonObject.getString("refresh");
        }
        logger.debug(token);
        return token;
    }
}
