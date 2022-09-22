package com.mushroom.common;

public enum ResultCodeEnum {
    OK(5200, "请求成功"),                // 通用相应
    ERROR(-5200, "请求错误"),            // 通用错误
    FORBIDDEN(-5403, "没有权限"),         // 无权限访问错误
    UNAUTHORIZED(-5401, "暂未登录或token已过期"),    // 还没有进行授权错误
    USER_NOT_FOUND(-54041, "用户不存在"); // 用户不存在

    public final int code;
    public final String defaultMessage;

    ResultCodeEnum(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
