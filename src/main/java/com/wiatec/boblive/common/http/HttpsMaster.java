package com.wiatec.boblive.common.http;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import com.wiatec.boblive.common.http.Request.GetRequest;
import com.wiatec.boblive.common.http.Request.PostRequest;
import com.wiatec.boblive.common.result.ResultMaster;
import com.wiatec.boblive.common.result.XException;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;

/**
 * @author patrick
 */
public class HttpsMaster {

    private static SSLContext sslContext;
    public static OkHttpClient okHttpClient;
    private static Logger logger = LoggerFactory.getLogger(HttpsMaster.class);
    private static final String KEY_STORE_FILE="/cert/cert.p12";
    private static final String KEY_STORE_PASS="123456";

    /*
     * okhttp client init
     */
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        try {
            builder.sslSocketFactory(getSSLContext().getSocketFactory()).hostnameVerifier((s, sslSession) -> true);
            okHttpClient = builder.build();
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.toString());
        }

    }

    /**
     * get request
     *
     * @param url request url
     * @return get request
     */
    public static GetRequest get(String url) {
        return new GetRequest(url);
    }

    /**
     * post request
     *
     * @param url request url
     * @return post request
     */
    public static PostRequest post(String url) {
        return new PostRequest(url);
    }

    private static SSLContext getSSLContext(){
        if(sslContext == null){
            try {
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(getKeyStore(),KEY_STORE_PASS.toCharArray());
                KeyManager[] keyManagers = kmf.getKeyManagers();TrustManager[] trustAllCerts =
                        new TrustManager[] { new X509TrustManager() {
                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }

                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

                        }
                };
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(keyManagers, trustAllCerts, new SecureRandom());
            } catch (Exception e) {
                throw new XException(ResultMaster.error(1019, e.getMessage()));
            }
        }
        return sslContext;
    }

    private static KeyStore getKeyStore(){
        KeyStore keyStore=null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            String productPath = System.getProperty("user.dir") + KEY_STORE_FILE;
            logger.debug(productPath);
            FileInputStream fis = new FileInputStream(new File(productPath));
            keyStore.load(fis, KEY_STORE_PASS.toCharArray());
            fis.close();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new XException(ResultMaster.error(1029, e.getMessage()));
        }
        return keyStore;
    }

    public static void main (String [] args){
        String s = HttpsMaster.post("https://ws.voucher4u.eu/v1/token/create").execute();
        logger.debug(s);
    }

}
