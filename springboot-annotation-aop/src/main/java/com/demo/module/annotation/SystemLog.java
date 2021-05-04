package com.demo.module.annotation;

import java.lang.annotation.*;

/**
 * <p> @Title SystemLog
 * <p> @Description 接口日志注解
 *
 * @author ACGkaka
 * @date 2021/4/1 11:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module()  default "";//模块
    String method()  default "";//方法
    String operateType() default "OTHER" ;//事件类型：LOGIN；LOGINOUT；ADD；DELETE；UPDATE；SELETE；UPLOAD；DOWNLOAD；OTHER
    String logType() default "0";//日志类型：0：系统日志；1：业务日志
}
