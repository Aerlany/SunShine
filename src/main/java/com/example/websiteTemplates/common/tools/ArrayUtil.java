package com.example.websiteTemplates.common.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    //字符串转化为数组
    public static String[] stringToOneDArray(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            if (jsonString == null || jsonString.equals("")) {
                return new String[0];
            }
            jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode.isArray()) {
                int length = jsonNode.size();
                String[] array = new String[length];

                for (int i = 0; i < length; i++) {
                    array[i] = jsonNode.get(i).asText();
                }

                return array;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断数组中是否含有某个元素
    public static Boolean isContain(String[] targetArray, String string) {
        return Arrays.asList(targetArray).contains(string);
    }

    //删除数组中指定元素
    public static String[] removeElementFromList(String[] targetArray, String element) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(targetArray));
        list.remove(element); // 移除指定元素，如果存在的话
        return list.toArray(new String[0]);
    }

    //将数组转换为列表
    public static List<String> arrayToList(String[] array) {
        if (array == null || array.length == 0){
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(array));
    }

}
