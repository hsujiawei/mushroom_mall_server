package com.mushroom.common.exceptions;

import com.mushroom.common.ResultCodeEnum;

/**
 * 该异常表示用户不存在异常
 */
public class UserNotFoundException extends BaseException{

    public UserNotFoundException(ResultCodeEnum resultCode) {
        super(resultCode.defaultMessage);
        this.code = resultCode.code;
    }
}
