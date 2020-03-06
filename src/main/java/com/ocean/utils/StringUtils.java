package com.ocean.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtils {

    /**
     * 判断字符串是否为空
     */
    public static Boolean isBlank(String origin) {
        return origin == null || "".equals(origin) || origin.trim().length() == 0;
    }

    /**
     * 拼接字符串，分号分隔
     */
    public static String append(List<String> list) {
        return append(list, Constants.SEMICOLON);
    }

    /**
     * 拼接字符串
     */
    public static String append(List<String> list, String separator) {
        if (list == null || list.size() == 0) {
            return "";
        }
        if(isBlank(separator)) {
            append(list);
        }

        StringBuilder builder = new StringBuilder();
        for (String item : list) {
            builder.append(item).append(separator);
        }
        String result = builder.substring(0, builder.length() - 1);

        return result;
    }

    /**
     * 字符串转列表，分号分隔
     *
     * @param source 源字符串
     * @return 字符串列表
     */
    public static List<String> stringToList(String source) {
        return stringToList(source, Constants.SEMICOLON);
    }

    /**
     * 字符串转列表
     *
     * @param source 源字符串
     * @param separator 分隔符
     * @return 字符串列表
     */
    public static List<String> stringToList(String source, String separator) {
        if(isBlank(source)) {
            return Collections.emptyList();
        }
        if(isBlank(separator)) {
            stringToList(source);
        }

        return Arrays.asList(source.split(separator));
    }

}
