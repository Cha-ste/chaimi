package com.ocean.enums;

/**
 * 上下架枚举
 */
public enum PutawayEnum {
    DOWN(0, "下架"),
    UP(1, "上架");

    Integer code;
    String value;

    PutawayEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
