package com.mushroom.common.exceptions;

/**
 * 该抽象类是本系统中所有自定义异常的基异常，用于指定异常的统一格式
 */
public abstract class BaseException extends RuntimeException{
    protected int code;

    protected BaseException(String message) {
        super(message);
    }

    public int getCode() {
        return this.code;
    }
}
