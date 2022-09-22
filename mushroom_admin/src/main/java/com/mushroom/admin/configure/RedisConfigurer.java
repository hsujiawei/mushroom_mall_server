package com.mushroom.admin.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfigurer {

    @Bean   // 对 Redis 的 key 和 value 序列化进行配置
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置 Redis 连接工厂
        redisTemplate.setConnectionFactory(factory);

        // 设置 key 和 value 的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());    // 键序列化为字符串
        redisTemplate.setValueSerializer(redisSerializer());
        // 设置 Hash 的 key-value 序列化规则
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(redisSerializer());

        // 设置支持事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {  // 设置特定的序列化器
        // 创建 Jackson 的 JSON 序列化器
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 创建 ObjectMapper 对象，该对象是 Jackson 序列化的核心
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置所有的成员变量都可以序列化，而不是仅仅是 public 成员变量
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 必须要进行下面的设置，否则无法将 JSON 反序列化为对象，会被反序列化为 Map 类型
         objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        return serializer;

    }
}

//  JdkSerializationRedisSerializer 默认的序列化机制，使用 JDK 本身的序列化功能，二进制流
//  StringRedisSerializer 将 key 或 value 序列化为字符串
//  Jackson2JsonRedisSerializer 可以将 pojo 实例序列化成 json 格式存储在 redis 中，也可以将 json 格式的数据转换成 pojo 实例
//  GenericFastJsonRedisSerializer 另一种 javabean 与 json 之间的转换，同时也需要指定 Class 类型
