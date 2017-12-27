package com.websocket.server;

import com.google.gson.Gson;

/**
 * @author halochen
 * @since 2017-04-07
 */
public class JsonUtil {
    private final static Gson gson = new Gson();
    public static  <T> T jsonToBean(Object jsonObj,Class<T> t){
        if (jsonObj == null)return null;
        String jsonStr = gson.toJson(jsonObj);
        return gson.fromJson(jsonStr, t);
    }

    public static String objectToJsonStr(Object object){
        if (object == null)return null;
        String jsonStr = gson.toJson(object);
        return jsonStr;
    }

    public static <T> T jsonToBean(String jsonStr,Class<T> t){
        if (jsonStr == null)return null;
        return gson.fromJson(jsonStr, t);
    }
}
