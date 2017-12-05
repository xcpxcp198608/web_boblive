package com.wiatec.boblive.common.http.Request;


import java.util.Map;

import com.wiatec.boblive.common.http.configuration.Header;
import com.wiatec.boblive.common.http.configuration.Parameters;
import okhttp3.Headers;
import okhttp3.Request;


public class GetRequest extends RequestMaster {

    private String url;
    public GetRequest(String url) {
        this.url = url;
    }

    @Override
    protected Request createRequest(Header header, Parameters parameters , Object tag) {
        Request.Builder builder = new Request.Builder();
        if(header !=null){
            Headers headers = Headers.of(header.stringMap);
            builder.headers(headers);
            builder.header("Accept", "application/json");
            builder.header("connection", "Keep-Alive");
            builder.header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        }
        if(parameters != null){
            StringBuilder stringBuilder = new StringBuilder(url);
            stringBuilder.append("?");
            for(Map.Entry<String,String> entry : parameters.stringMap.entrySet()){
                stringBuilder.append(entry.getKey()+"=").append(entry.getValue()+"&");
            }
            url = stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
        }
        if(tag != null){
            builder.tag(tag);
        }
        //Logger.d(url);
        builder.get().url(url);
        return builder.build();
    }
}
