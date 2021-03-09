package com.mule.custom.logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
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


    public static String formatJson(String jsonString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = gson.fromJson(jsonString,JsonElement.class);
        return gson.toJson(json);
    }
}
