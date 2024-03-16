package com.example.mydfs_back.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 将对象转换为 JSON 字符串
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 将 JSON 字符串转换为指定类型的对象
    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}