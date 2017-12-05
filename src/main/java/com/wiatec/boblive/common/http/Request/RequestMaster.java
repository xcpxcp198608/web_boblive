package com.wiatec.boblive.common.http.Request;


import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wiatec.boblive.common.http.HttpsMaster;
import com.wiatec.boblive.common.http.configuration.Header;
import com.wiatec.boblive.common.http.configuration.Parameters;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public abstract class RequestMaster {
    private Header header;
    private Parameters parameters;
    private Object mTag;
    private Map<Object ,Call> callMap = new ConcurrentHashMap<>();

    public RequestMaster() {
        parameters = new Parameters();
        header = new Header();
    }

    public RequestMaster tag(Object tag){
        this.mTag = tag;
        return this;
    }

    public RequestMaster parames(String key , String  value){
        parameters.put(key ,value);
        return this;
    }

    public RequestMaster parames(String key , File value){
        parameters.put(key ,value);
        return this;
    }

    public RequestMaster parames(String key , Object value){
        parameters.put(key ,value);
        return this;
    }

    public RequestMaster parames(Parameters parameters){
        this.parameters = parameters;
        return this;
    }

    public RequestMaster headers(String key ,String value){
        header.put (key ,value);
        return this;
    }

    public RequestMaster headers(Header header){
        this.header = header;
        return this;
    }

    protected abstract Request createRequest(Header header, Parameters parameters ,Object tag);

    //同步请求
    public String execute(){
        try {
            Request request = createRequest(header, parameters, mTag);
            Call call = HttpsMaster.okHttpClient.newCall(request);
            Response response = call.execute();
            return response.body().string();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //异步执行请求
    public void enqueue (Callback callback){
        try {
            Request request = createRequest(header, parameters, mTag);
            Call call = HttpsMaster.okHttpClient.newCall(request);
            call.enqueue(callback);
            if (mTag != null) {
                callMap.put(mTag, call);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //通过标签取消请求
    public void cancel (Object tag){
        Call call = callMap.get(tag);
        if(call != null) {
            call.cancel();
        }
    }
    //取消所有请求
    public void cancelAll(){
        HttpsMaster.okHttpClient.dispatcher().cancelAll();
    }
}
