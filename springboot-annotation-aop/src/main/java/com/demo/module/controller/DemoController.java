package com.demo.module.controller;

import com.demo.module.annotation.SystemLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
