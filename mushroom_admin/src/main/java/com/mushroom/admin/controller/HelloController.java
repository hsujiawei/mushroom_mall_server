package com.mushroom.admin.controller;

import com.mushroom.common.CommonResult;
import com.mushroom.common.ResultCodeEnum;
import com.mushroom.common.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/data")
    public CommonResult data() {

        return CommonResult.ERROR();
    }

    @GetMapping("/user")
    public String getUser(int userId) {
        if (userId < 10)
            throw new UserNotFoundException(ResultCodeEnum.USER_NOT_FOUND);

        return "Success";
    }
}
