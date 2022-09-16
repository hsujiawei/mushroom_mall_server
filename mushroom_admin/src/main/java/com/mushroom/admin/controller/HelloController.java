package com.mushroom.admin.controller;

import com.mushroom.common.CommonResult;
import com.mushroom.common.ResultCodeEnum;
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
       return new CommonResult(ResultCodeEnum.OK).data("user", "Nice");
    }
}
