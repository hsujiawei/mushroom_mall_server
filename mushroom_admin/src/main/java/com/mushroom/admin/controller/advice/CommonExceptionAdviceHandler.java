package com.mushroom.admin.controller.advice;

import com.mushroom.common.CommonResult;
import com.mushroom.common.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommonExceptionAdviceHandler {

    @ExceptionHandler(BaseException.class)
    public CommonResult userNotFound(BaseException e) {
        return CommonResult.ERROR().code(e.getCode()).message(e.getMessage());
    }
}
