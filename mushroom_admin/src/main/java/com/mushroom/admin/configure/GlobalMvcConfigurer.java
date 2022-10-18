package com.mushroom.admin.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局 MVC 配置
 */
@Configuration
public class GlobalMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {    // 跨越配置
        registry.addMapping("/**")      // 所有资源都允许跨域访问
                // .allowedOrigins("*")            // 当开启允许携带凭证时，不能使用该方法来进行设置
                .allowedOriginPatterns("*")        // 允许所有来源访问，携带请求凭证时需要使用该方法来指定 original
                .allowedMethods("*")               // 允许所有方法跨域调用
                .allowedHeaders("*")               // 放行所有原始的请求头
                .exposedHeaders("Authorization")    // 将 Authorization 暴露出去
                .allowCredentials(true);            // 运行跨域发送 Cookie
    }
}
