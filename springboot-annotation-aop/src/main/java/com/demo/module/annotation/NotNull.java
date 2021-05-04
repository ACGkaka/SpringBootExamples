package com.demo.module.annotation;

import java.lang.annotation.*;

/**
 * <p> @Title NotNull
 * <p> @Description 不为空注解
 *
 * @author ACGkaka
 * @date 2021/4/30 16:56
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
}
