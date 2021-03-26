package com.mule.custom.logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.List;
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

    public static void validateList(List<Properties> inputList, Map<String,Object> resultMap){
        for(Properties input:inputList){
            if(input.getKey() !=null && input.getValue() !=null) {
                resultMap.put(input.getKey(), input.getValue());
            }
        }
    }

    public static boolean checkArray( Object obj) {
        return obj.getClass().isArray();
    }


    public static String formatJson(String jsonString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = gson.fromJson(jsonString,JsonElement.class);
        return gson.toJson(json);
    }
}
