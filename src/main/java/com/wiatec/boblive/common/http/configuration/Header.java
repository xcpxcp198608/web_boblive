package com.wiatec.boblive.common.http.configuration;


import com.wiatec.boblive.common.utils.TextUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Header {
    public Map<String ,String> stringMap = new ConcurrentHashMap<>();

    private String key;
    private String value;

    public Header (){

    }

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
        stringMap.put(key ,value);
    }

    public void put (String key ,String value){
        if(TextUtil.isEmpty(key) || TextUtil.isEmpty(value)){
            return;
        }
        stringMap.put(key ,value);
    }

    public String get (String key){
        return stringMap.get(key);
    }
}
