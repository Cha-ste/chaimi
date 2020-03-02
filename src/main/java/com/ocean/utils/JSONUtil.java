package com.ocean.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 */
public class JSONUtil {

    /**
     * JSON字符串转map
     */
    public static Map<String, Object> parseMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, HashMap.class);
    }

    /**
     * JSON字符串转列表
     */
    public static <T> List parseList(String jsonStr, Class<T> tClass) {
        List<T> list = JSONObject.parseArray(jsonStr, tClass);
        return list;
    }

    /**
     * 获取JSON字符串中key对应的value
     *
     * @param jsonStr JSON字符串
     * @param key     键
     * @return value
     */
    public static String getValue(String jsonStr, String key) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return getValue(jsonObject, key);
    }

    /**
     * 获取JSON对象中key对应的value
     * @param object JSON对象
     * @param key     键
     * @return value
     */
    public static String getValue(JSONObject object, String key) {
        String value = object.getString(key);
        return StringUtils.isBlank(value) ? null : value;
    }

}
