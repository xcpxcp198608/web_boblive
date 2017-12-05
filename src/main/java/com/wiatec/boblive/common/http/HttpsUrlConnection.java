package com.wiatec.boblive.common.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;


public class HttpsUrlConnection {
    private static final String KEY_STORE_FILE="/Users/xuchengpeng/IdeaProjects/boblive/src/main/resources/cert/cert.p12";
    private static final String KEY_STORE_PASS="123456";
    private static final String TRUST_STORE_FILE="/Users/xuchengpeng/IdeaProjects/boblive/src/main/resources/cert/client.keystore";
    private static final String TRUST_STORE_PASS="123456";

    private static SSLContext sslContext;

    public static String get(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            if(connection instanceof HttpsURLConnection){
                ((HttpsURLConnection)connection)
                        .setSSLSocketFactory(getSSLContext().getSocketFactory());
            }
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            connection.connect();
            if(connection.getResponseCode()==200){
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
            }else{
                in = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream()));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String post(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            if(conn instanceof HttpsURLConnection){
                ((HttpsURLConnection)conn)
                        .setSSLSocketFactory(getSSLContext().getSocketFactory());
            }
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            if(conn.getResponseCode()==200){
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
            }else{
                in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    private static SSLContext getSSLContext(){
        long time1=System.currentTimeMillis();
        if(sslContext==null){
            try {
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(getKeyStore(),KEY_STORE_PASS.toCharArray());
                KeyManager[] keyManagers = kmf.getKeyManagers();
//                TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance("SunX509");
//                trustManagerFactory.init(getTrustStore());
//                TrustManager[]  trustManagers= trustManagerFactory.getTrustManagers();
                TrustManager[] trustAllCerts = new TrustManager[] {
                        new X509TrustManager() {
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

                        }
                };
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(keyManagers, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long time2=System.currentTimeMillis();
        System.out.println("SSLContext 初始化时间："+(time2-time1));
        return sslContext;
    }


    private static KeyStore getKeyStore(){
        KeyStore keyStore=null;
        FileInputStream fis = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            fis = new FileInputStream(new File(KEY_STORE_FILE));
            keyStore.load(fis, KEY_STORE_PASS.toCharArray());
            fis.close();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return keyStore;
    }

    private static KeyStore getTrustStore() throws IOException{
        KeyStore trustKeyStore=null;
        FileInputStream fis=null;
        try {
            trustKeyStore=KeyStore.getInstance("JKS");
            fis = new FileInputStream(new File(TRUST_STORE_FILE));
            trustKeyStore.load(fis, TRUST_STORE_PASS.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return trustKeyStore;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Long time1=System.currentTimeMillis();
        String result=get("https://pay.ws.test.ecoupon.pro/transaction/123abcDEF/info", "");
        System.out.println(result);
        Long time2=System.currentTimeMillis();
        System.out.println("耗费时间:"+(time2-time1));
    }
}
