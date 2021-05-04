## 一、AOP的简单介绍

### 1. AOP是什么

> AOP（Aspect Oriented Programming）–面向切面编程
>
> * 通过预编译方式和运行期动态代理实现在不修改源代码的情况下给程序动态统一添加功能的一种技术。
>
> * 实现方式：JDK动态代理、CGLIB代理。前者基于接口，后者基于子类。

### 2. AOP能做什么

* 统计接口访问次数
* 增强功能：在不改动代码的情况下，为接口增加一些额外的功能

### 3. 切面执行顺序

正常：

![切面执行顺序-正常](https://img-blog.csdnimg.cn/2021040110585346.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


异常：

![切面执行顺序-异常](https://img-blog.csdnimg.cn/20210401105923843.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


### 4. AOP注解

* **@Aspect**：切面，通常是一个类的注解，里面可以定义切入点和通知。

* **@Pointcut**：切入点，书写切入点表达式，指明Advice要在什么样的条件下才能被触发。

  由下列方式来定义或者通过 &&、 ||、 !、 的方式进行组合：

  - *execution*：用于匹配方法执行的连接点；
  - *within*：用于匹配指定类型内的方法执行；
  - *this*：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；    
  - *target*：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
  - *args*：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
  - *@within*：用于匹配所以持有指定注解类型内的方法；
  - *@target*：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
  - *@args*：用于匹配当前执行的方法传入的参数持有指定注解的执行；
  - *@annotation*：用于匹配当前执行方法持有指定注解的方法；

* **Advice**：通知，某个连接点所采用的处理逻辑，也就是向连接点注入的代码， AOP在特定的切入点上执行的增强处理。

  * *@Before*：标识前置增强方法，相当于BeforeAdvice；
  * *@Around*：环绕增强，相当于MethodInterceptor；
  * *@After*：<font color="red">final增强</font>，抛出异常和正常退出后都会执行；
  * *@AfterReturning*：后置增强，正常退出时执行，相当于AfterReturningAdvice；
  * *@AfterThrowing*：后置增强，抛出异常时执行，相当于ThrowsAdvice。

* **JointPoint**：连接点，程序运行中的某个阶段点，比如方法的调用、异常的抛出等。

  * *Object[] getArgs*：返回目标方法的参数；
  * *Signature getSignature*：返回目标方法的签名；
  * *Object getTarget*：返回被织入增强处理的目标对象；
  * *Object getThis*：返回AOP框架为目标对象生成的代理对象。

* **Advisor**：增强， 是PointCut和Advice的综合体，完整描述了一个advice将会在pointcut所定义的位置被触发。

  ```xml
  <aop:aspectj-autoproxy/>
     <aop:config proxy-target-class="true">
         <aop:pointcut id="servicePointcut"
                       expression="execution(* com.cpic..*Service.*(..))" />
         <aop:advisor pointcut-ref="servicePointcut" advice-ref="txAdvice"
                      order="3" />
     </aop:config>
     <tx:advice id="txAdvice" transaction-manager="transactionManager">
         <tx:attributes>
             <tx:method name="list*" read-only="true" />
             <!-- log方法会启动一个新事务 -->
             <tx:method name="log*" propagation="REQUIRES_NEW"
                        isolation="READ_COMMITTED" />
         </tx:attributes>
     </tx:advice>
   
  <!-- OK所以一个Spring增强（advisor）=切面(advice)+切入点(PointCut) -->
  ```



### 5. 自定义注解

**@Target**：描述了注解修饰的对象范围，取值在`java.lang.annotation.ElementType`定义，常用的包括：

- METHOD：用于描述方法
- PACKAGE：用于描述包
- PARAMETER：用于描述方法变量
- TYPE：用于描述类、接口或enum类型

**@Retention**: 表示注解保留时间长短。取值在`java.lang.annotation.RetentionPolicy`中，取值为：

- SOURCE：在源文件中有效，编译过程中会被忽略
- CLASS：随源文件一起编译在class文件中，运行时忽略
- RUNTIME：在运行时有效

> 注意：
>
> * 只有定义为`RetentionPolicy.RUNTIME`时，我们才能通过注解反射获取到注解。
> * 所以，假设我们要自定义一个注解，它用在字段上，并且可以通过反射获取到，功能是用来描述字段的长度和作用。

**@Documented**：表示这个注解是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注解了文档化，它的注解成为公共API的一部分。



**示例-反射获取注解**

定义注解：

```java
@Target(ElementType.FIELD)  //  注解用于字段上
@Retention(RetentionPolicy.RUNTIME)  // 保留到运行时，可通过注解获取
public @interface MyField {
    String description();
    int length();
}
```

通过反射获取注解

```java
public class MyFieldTest {

    //使用我们的自定义注解
    @MyField(description = "用户名", length = 12)
    private String username;

    @Test
    public void testMyField(){

        // 获取类模板
        Class c = MyFieldTest.class;

        // 获取所有字段
        for(Field f : c.getDeclaredFields()){
            // 判断这个字段是否有MyField注解
            if(f.isAnnotationPresent(MyField.class)){
                MyField annotation = f.getAnnotation(MyField.class);
                System.out.println("字段:[" + f.getName() + "], 描述:[" + annotation.description() + "], 长度:[" + annotation.length() +"]");
            }
        }

    }
}
```


## 二、实战代码

直接上图，先大致看看项目结构：

<img src="https://img-blog.csdnimg.cn/20210402103223833.png" width="60%">

### 1. 依赖引入

```xml
<!-- AOP -->
<dependency>
	<groupId>org.aspectj</groupId>
	<artifactId>aspectjrt</artifactId>
	<version>1.9.4</version>
</dependency>
<dependency>
	<groupId>org.aspectj</groupId>
	<artifactId>aspectjweaver</artifactId>
	<version>1.9.4</version>
</dependency>
<dependency>
	<groupId>cglib</groupId>
	<artifactId>cglib</artifactId>
	<version>3.2.12</version>
</dependency>
```

### 2. AOP示例

```java
import com.demo.module.annotation.SystemLog;
import com.demo.module.utils.AtomicCounter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     *demo
     */
    @Pointcut(demo)
    public void log() {
    }

    /**
     * 在接口原有com.demo执行此处的代码
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
```

### 3. 自定义注解示例

```java
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
```



### 4. 定义接口，进行测试

```java
import com.demo.module.annotation.SystemLog;
import org.springframework.web.bind.annotation.GetMapcom.demot org.springframework.web.bind.annotation.RestController;

/**
 * <p> @Title DemoController
 * <p> @Description 待统计接口
 *
 * @author ACGkaka
 * @date 2021/3/31 17:24
 */
@RestController
public class DemoController {

    @GetMapping("/index")
    @SystemLog(module = "首页", method = "hello", operateType = "SELECT", logType = "1")
    public String index() {
        return "<h1>Hello World.</h1>";
    }

    @GetMapping("login")
    @SystemLog(module = "首页", method = "login", operateType = "LOGIN", logType = "1")
    public String login() {
        int i = 1 / (Math.random() > 0.5 ? 0 : 1);
        return "测试报错的AOP方法";
    }
}
```



### 5. 源码地址

[https://github.com/ACGkaka/aop-demo](https://github.com/ACGkaka/aop-demo)