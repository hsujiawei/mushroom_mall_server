package com.mushroom.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 加载 Common 模块下的配置
@SpringBootApplication(scanBasePackages = {"com.mushroom.admin", "com.mushroom.common"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
