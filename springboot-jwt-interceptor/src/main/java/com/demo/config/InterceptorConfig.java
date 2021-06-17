package com.demo.config;

import com.demo.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p> @Title InterceptorConfig
 * <p> @Description 拦截器配置
 *
 * @author ACGkaka
 * @date 2021/5/6 16:02
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index/login");
    }

    @Bean
    public TokenInterceptor authenticationInterceptor() {
        return new TokenInterceptor();
    }
}
