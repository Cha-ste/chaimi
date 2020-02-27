package com.ocean.enums;

/**
 * 文件类型枚举
 */
public enum FileType {
    VIDEO("video"),
    IMAGE("image"),
    OFFICE("office"),
    OTHER("other");

    private String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
