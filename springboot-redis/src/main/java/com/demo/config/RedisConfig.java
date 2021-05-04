package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * <p> @Title LettuceRedisConfig
 * <p> @Description Lettuce Redis客户端配置
 *
 * @author ACGkaka
 * @date 2020/12/9 15:16
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate
     *
     * @param redisConnectionFactory 连接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //设置key的存储方式为字符串
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置为value的存储方式为JDK二进制序列化方式，还有jackson序列化方式（Jackson2JsonRedisSerialize）
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
