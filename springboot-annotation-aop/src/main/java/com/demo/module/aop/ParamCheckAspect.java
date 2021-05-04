package com.demo.module.aop;

import com.demo.module.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p> @Title ParamCheckAspect
 * <p> @Description 参数检查切面
 *
 * @author ACGkaka
 * @date 2021/4/30 16:52
 */
@Component
@Aspect
public class ParamCheckAspect {

    /**
     * 定义切面
     * - 此处代表com.demo.module.controller包下的所有接口都会被统计
     */
    @Pointcut("execution(* com.demo.module..*.*(@com.demo.module.annotation.NotNull (*), ..))")
    public void notNull(){

    }

    /**
     * 在接口原有的方法执行前，将会首先执行此处的代码
     */
    @Before("notNull()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取传入目标方法的参数
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        List<String> argNames = Arrays.asList(((MethodSignature) joinPoint.getSignature()).getParameterNames());
        List<String> nullNames = new ArrayList<>();
        for (int i = 0; i < args.size(); i++) {
            if (Objects.isNull(args.get(i))) {
                nullNames.add(argNames.get(i));
            }
        }
        if (nullNames.size() > 0) {
            throw new MyException(400, "参数异常，不能为空");
        }
    }

}
