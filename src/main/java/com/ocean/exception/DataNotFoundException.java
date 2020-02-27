package com.ocean.exception;

/**
 * 数据不存在异常
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
