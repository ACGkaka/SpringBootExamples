package com.demo.module.aop;

import com.demo.module.annotation.SystemLog;
import com.demo.module.utils.AtomicCounter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> @Title ApiVisitHistory
 * <p> @Description API访问历史统计
 *
 * @author ACGkaka
 * @date 2021/3/31 17:29
 */
@Component
@Aspect
public class ApiLogAopAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLogAopAction.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    HttpServletRequest request;

    /**
     * 定义切面
     * - 此处代表com.demo.module.annotation.SystemLog 注解修饰的所有接口都会被统计
     *
     * - 补充：如下注解方式可以统计所有被自定义@NotNull参数修饰的接口
     * - @Pointcut("execution(* com.demo.module..*.*(@com.demo.module.annotation.NotNull (*), ..))")
     */
    @Pointcut("@annotation(com.demo.module.annotation.SystemLog)")
    public void log() {
    }

    /**
     * 在接口原有的方法执行前，将会首先执行此处的代码
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        SystemLog annotation = signature.getMethod().getAnnotation(SystemLog.class);
        AtomicCounter.init(annotation.module(), request.getRequestURI());
        // 计数
        AtomicCounter.getInstance().increaseVisit(request.getRequestURI());
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    /**
     * 只有正常返回才会执行此方法
     * 如果程序执行失败，则不执行此方法
     */
    @AfterReturning(returning = "returnVal", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        LOGGER.info("URI:[{}], 耗费时间:[{}] ms, 访问次数:{}", request.getServletPath(), System.currentTimeMillis() - startTime.get(), AtomicCounter.getInstance().increaseSuccess(request.getRequestURI()));
    }

    /**
     * 当接口报错时执行此方法
     */
    @AfterThrowing(pointcut = "log()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        LOGGER.info("接口访问失败，URI:[{}], 耗费时间:[{}] ms", request.getServletPath(), AtomicCounter.getInstance().increaseFail(request.getRequestURI()));
    }

    /**
     * 在接口原有的方法执行前，将会首先执行此处的代码
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("End.{}", AtomicCounter.getInstance().getValue(request.getRequestURI()));
    }
}