package com.mushroom.common;

public enum ResultCodeEnum {
    OK(5200, "请求成功"),                                   // 通用请求成功
    ERROR(-5200, "请求错误"),                               // 通用请求错误
    FORBIDDEN(-5403, "没有权限"),                           // 无权限访问错误
    UNAUTHORIZED(-5401, "暂未登录或token已过期"),             // 还没有进行授权错误

    // 用户相关
    USER_NOT_FOUND(-54041, "该用户不存在"),                   // 用户不存在
    USER_UPDATE_FAILURE(-55042, "用户信息更新失败"),           // 更新用户信息
    USER_PASSWORD_NOT_MATCH(-54042, "用户密码错误"),         // 用户密码错误
    USER_AUTHENTICATION_SUCCESS(-59922, "用户认证成功"),     // 用户认证成功

    // Token 相关
    TOKEN_HAS_EXPIRED(-882, "登录已过期，请重新登录"),         // 用户 token 已过期
    TOKEN_INVALID(-883, "非法令牌"),                        // 用户 token 非法

    // 分类操作异常
    CATEGORY_MANIPULATE_FAILURE(-1123, "操作失败"),

    // 验证码相关
    VC_CODE_NOT_FOUND_ERROR(-11223, "验证码不存在，请携带Cookie进行请求"),    // 从请求中获取不到对应的 Cookie
    VC_CODE_NOT_MATCH(-11227, "验证码错误"),
    HAVE_NOT_AUTHORIZATION_HEADER(-885, "请在 Authorization 请求头中携带 token");   // 没有 Authorization 请求头


    public final int code;
    public final String defaultMessage;

    ResultCodeEnum(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
