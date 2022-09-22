package com.mushroom.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public final class CommonResult {
    private int code;                 // 响应码
    private String message;                 // 响应消息
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")    // 指定日期的格式和时区
    private final Date responseTime;        // 响应时间
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // data 中没有数据时不返回
    private final Map<String, Object> data = new HashMap<>(); // 响应数据

    // 构造方法只接受 ResultCodeEnum 枚举类型，为了更好的管理和统一
    private CommonResult(ResultCodeEnum resultCode) {
        this.code = resultCode.code;                // 设置枚举中的响应码
        this.message = resultCode.defaultMessage;   // 设置枚举中的默认消息
        this.responseTime = new Date();             // 设置响应时间
    }
    // 请求只有两种情况：成功和失败
    public static CommonResult OK() {
        return new CommonResult(ResultCodeEnum.OK);
    }
    public static CommonResult ERROR() {
        return new CommonResult(ResultCodeEnum.ERROR);
    }

    // 修改响应的 message
    public CommonResult message(String message) {
        this.message = message;
        return this;
    }
    // 修改响应的 code
    public CommonResult code(int code) {
        this.code = code;
        return this;
    }

    // 设置响应的数据
    public CommonResult data(Map<String, Object> data) {    // 接受传入一个 Map
        if (data.size() > 0) // 如果传入的 Map 有数据
            this.data.putAll(data); // 将传入的 Map 中的全部数据添加到 data 实例成员中
        return this;
    }
    public CommonResult data(String key, Object value) {
        this.data.put(key, value);  // 将 key 和 value 添加到 Map 中
        return this;
    }
}
