package com.demo.example.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p> @Title MyKeyGenerator
 * <p> @Description 自动生成缓存key配置
 *
 * @author zhj
 * @date 2021/12/28 16:31
 */
@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... params) {
        // 设置缓存key格式（方法名[参数值数组]）
        return String.format("%s[%s]", method.getName(), Arrays.asList(params).toString());
    }
}
