package com.wiatec.boblive.common.http.Request;


import java.util.Map;

import com.wiatec.boblive.common.http.configuration.Header;
import com.wiatec.boblive.common.http.configuration.Parameters;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostRequest extends RequestMaster {

    private String url;

    public PostRequest (String  url){
        this.url = url;
    }

    @Override
    protected Request createRequest(Header header, Parameters parameters , Object tag) {
        Request.Builder builder = new Request.Builder();
        if(header != null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
            builder.header("Accept", "application/json");
            builder.header("connection", "Keep-Alive");
            builder.header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        }
        if(parameters != null){
            RequestBody requestBody;
            if(isJson){
                requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mJson);
            }else {
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : parameters.stringMap.entrySet()) {
                    stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                requestBody = RequestBody.create(mediaType, stringBuilder.toString());
            }
            builder.post(requestBody);
        }
        if(tag != null){
            builder.tag(tag);
        }
        builder.url(url);
        return builder.build();
    }
}
