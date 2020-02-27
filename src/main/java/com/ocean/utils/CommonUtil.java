package com.ocean.utils;

public class CommonUtil {
    /**
     * 判断数据是否为空或者为零
     * @return 是或否
     */
    public static Boolean isNullOrZero(Integer num) {
        return num == null || num.equals(0);
    }
}
