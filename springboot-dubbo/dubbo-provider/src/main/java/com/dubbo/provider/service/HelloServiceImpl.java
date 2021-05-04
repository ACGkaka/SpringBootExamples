package com.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * <p> @Title HelloServiceImpl
 * <p> @Description Dubbo 服务提供者
 *
 * @author ACGkaka
 * @date 2021/4/23 13:26
 */
@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
