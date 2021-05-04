

Dubbo 是一个分布式服务框架，致力于提供高性能和透明化的 RPC 远程服务调用方案，以及 SOA 服务治理方案

## 一、 Dubbo的简单介绍

### 1. 网站架构的发展历程  

网站架构随着业务的发展，逻辑越来越复杂，数据量越来越大，交互越来越多.......

![网站架构的发展历程](https://img-blog.csdnimg.cn/20210331170340619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


- 单一应用架构（ORM）

当网站流量很小时，将所有的功能部署到一起，以减少部署节点和成本。此时，只需要使用简化增删改查的工作量，采用数据访问框架(ORM)。

- 垂直应用架构 （MVC）

当访问量逐渐增大，单一应用带来的加速度越来越小。此时，将应用拆分成互不相干的几个应用，所以采用MVC框架。

- 分布式服务架构 （RPC）

当垂直应用越来越多，应用之交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速地响应多变的市场需求。此时，用于提高业务复用及整合的服务框架(RPC)是关键。

- 面向服务框架 （SOA）

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的 资源调度和治理中心(SOA) 是关键。

### 2. dubbo核心工作原理
![dubbo核心工作原理](https://img-blog.csdnimg.cn/20210331170436258.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)



| 节点      | 角色说明                                                     |
| --------- | ------------------------------------------------------------ |
| Provider  | 暴露服务的服务提供方                                         |
| Registry  | 服务的注册与发现的注册中心，如zookeper(推荐)、multicast、redis、simple |
| Consumer  | 调用远程服务的服务消费方                                     |
| Monitor   | 统计服务的调用次数和调用时间的监控中心                       |
| Container | 服务运行容器                                                 |

#### 调用流程

1. 启动Zookeeper，执行 <font color="red">zkServer.cmd</font>；
   Zookeeper官方下载地址：[https://zookeeper.apache.org/releases.html](https://zookeeper.apache.org/releases.html)

2. 启动服务提供者，<font color="red">provider</font>；

3. 启动服务消费者，<font color="red">consumer</font>；
   （服务消费者在启动时，向注册中心订阅自己所需的服务）

4. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于TCP长连接推送变更数据给消费者；

5. 服务消费者从提供的服务列表中，基于软负载均衡算法，选一台提供者进行调用，如果失败，则选择另一台调用；

6. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到检测中心。

### 3. dubbo特点

1. Dubbo 支持 RPC 调用，服务之间的调用性能会很好，效率很高

2. 支持多种序列化协议，如 Hessian(默认)、HTTP、WebService

3. 对比springcloud

   | 组件         | Dubbo         | Spring Cloud                 |
   | ------------ | ------------- | ---------------------------- |
   | 服务注册中心 | Zookeeper     | Spring Cloud Netflix Eureka  |
   | 服务监控     | Dubbo-monitor | Spring Boot Admin            |
   | 断路器       | 不完善        | Spring Cloud Netfix Hystrix  |
   | 服务网关     | 无            | Spring Cloud Netflix Gateway |
   | 服务配置     | 无            | Spring Cloud Config          |
   | 服务跟踪     | 无            | Spring Cloud Sleuth          |
   | 消息总线     | 无            | Spring Cloud Bus             |
   | 数据流       | 无            | Spring Cloud Stream          |
   | 批量任务     | 无            | Spring Cloud Task            |



## 二、 springboot-Dubbo搭建

直接上图，先大致看看项目结构：

![项目结构](https://img-blog.csdnimg.cn/20210331170608505.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)




* **dubbo-provider** 是所谓的服务提供者，springboot项目；

* **dubbo-consumer** 是服务消费者，springboot项目；

* **dubbo-api** 是服务提供者的接口API，最普通的maven项目。

### 1. 项目依赖

dubbo的jar包依赖(spring-boot)

```xml
<!-- dubbo依赖 -->
<dependency>
    <groupId>com.alibaba.spring.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>
```

注册中心zookeeper的jar包依赖

```xml
<!-- 引入zookeeper的依赖 -->
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.10</version>
</dependency>
```

### 2. 服务提供者

服务提供者dubbo-provider提供HelloService.sayHello()接口

```java
package com.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.HelloService;
import org.springframework.stereotype.Component;

//@Service是dubbo里的注解，作用是暴露服务，不要选择spring的@Service
@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
```

>还要在springboot的启动类上加一个开启Dubbo的注解

```java
package com.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//开启dubbo的自动配置
@EnableDubboConfiguration
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
```

### 3. 服务消费者

dubbo-consumer远程调用dubbo-consumer提供的服务HelloService.sayHello()接口

```java
package com.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference//dubbo注解，可以仔细了解下这个注解
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("BJQ"));
        return hello;
    }

}
```

同上，这个也需要让Dubbo开启自动配置

```java
package com.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 让Dubbo开启自动配置
@EnableDubboConfiguration
@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
```

### 4. 项目启动流程和结构

#### 1. 启动注册中心：

执行 <font color="red">zkServer.cmd</font>。



#### 2. 启动服务提供者 dubbo-provider

如下图，说明dubbo-provider启动，并且已经连上注册中心

![启动服务提供者](https://img-blog.csdnimg.cn/20210331170637218.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


#### 3. 启动服务消费者dubbo-consumer

![启动服务消费者](https://img-blog.csdnimg.cn/20210331170653701.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


#### 4. 服务调用

访问地址：http://localhost:9021/hello

![服务调用结果](https://img-blog.csdnimg.cn/20210331170709276.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzMjA0NzA5,size_16,color_FFFFFF,t_70)


## 三、项目地址

[springboot-dubbo-demo](https://github.com/ACGkaka/dubbo-demo)


完结撒花~