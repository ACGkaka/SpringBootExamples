package com.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> @Title HelloController
 * <p> @Description Dubbo 消费者
 *
 * @author ACGkaka
 * @date 2021/4/23 13:26
 */
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("BJQ"));
        return hello;
    }

}
