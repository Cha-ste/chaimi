package com.ocean.utils;

public class StringUtils {

    /**
     * 判断字符串是否为空
     */
    public static Boolean isBlank(String origin) {
        return origin == null || "".equals(origin) || origin.trim().length() == 0;
    }

}
