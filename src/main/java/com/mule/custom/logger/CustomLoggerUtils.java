package com.mule.custom.logger;

import java.util.Map;

public class CustomLoggerUtils {
    public static void validateKey(Map<String,Object> resultMap, Object obj, String fieldName){
        if(obj!=null){
            resultMap.put(fieldName,obj);
        }
    }

    public static void validateMap(Map<String,String> inputMap,Map<String,Object> resultMap){
        for(Map.Entry<String,String> input: inputMap.entrySet()){
            if(input.getValue() !=null) {
                resultMap.put(input.getKey(), input.getValue());
            }
        }
    }

    public static boolean checkArray( Object obj) {
        return obj.getClass().isArray();
    }
}
