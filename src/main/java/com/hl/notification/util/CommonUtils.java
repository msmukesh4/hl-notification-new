package com.hl.notification.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    private static Gson gson = new Gson();

    public static String convertStringToGsonString(String message, String threadKeyValue){
        Map<Object, Object> messageThreadKeyValueMap = new HashMap<>();
        messageThreadKeyValueMap.put("text", message);
        messageThreadKeyValueMap.put("thread", Map.of("threadKey", threadKeyValue));
        return gson.toJson(messageThreadKeyValueMap);
    }

    public static String generateUniqueIdentifier(){
        return UUID.randomUUID().toString();
    }


}
