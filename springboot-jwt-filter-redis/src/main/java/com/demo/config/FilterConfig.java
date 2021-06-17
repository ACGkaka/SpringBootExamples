package com.demo.config;

import com.demo.common.filter.AccessTokenFilter;
import com.demo.common.filter.ExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> @Title FilterConfig
 * <p> @Description 过滤器配置类
 *
 * @author ACGkaka
 * @date 2021/6/16 18:08
 */
@Configuration
public class FilterConfig {

    /**
     * 实例化Token过滤器
     *
     * @return Token过滤器
     */
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.addUrlPatterns("/*");
        registration.setFilter(new AccessTokenFilter());
        registration.setName("AccessTokenFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 实例化异常处理过滤器（用于处理Filter中的异常，全局异常处理捕获不到Filter中的异常）
     *
     * @return 异常处理过滤器
     */
    @Bean
    public FilterRegistrationBean exceptionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ExceptionFilter());
        registration.setName("ExceptionFilter");
        //此处尽量小，要比其他Filter靠前
        registration.setOrder(-1);
        return registration;
    }
}
