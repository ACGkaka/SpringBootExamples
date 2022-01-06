package com.demo.example.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * <p> @Title MyLogBackFilter
 * <p> @Description 定制控制台日志过滤器
 *
 * @author ACGkaka
 * @date 2022/1/6 11:15
 */
public class MyLogBackFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        // 指定打印日志类（供控制台用）
        if (event.getLoggerName().contains("org.springframework.boot")
                && !event.getLoggerName().contains("org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration")
                && !event.getLoggerName().contains("org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration")) {
            // 打印启动信息
            return FilterReply.ACCEPT;
        } else if (event.getLevel().equals(Level.ERROR)) {
            // 打印异常信息
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}
