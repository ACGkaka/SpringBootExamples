package com.demo.user.controller;

import com.demo.user.entity.User;
import com.demo.user.service.UserService;
import com.demo.util.RedisUtil;
import com.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title DemoController
 * <p> @Description 登录页面
 *
 * @author ACGkaka
 * @date 2021/5/6 14:01
 */
@RestController
@RequestMapping("/index")
public class DemoController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @return Token
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login() {
        User user = userService.findUser();
        // 生成Token
        String token = TokenUtil.createToken();
        // 将Token存到Redis
        redisUtil.setWithPrefix(token, String.valueOf(user.getId()));
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", token);
        return result;
    }

    /**
     * 获取Token信息
     *
     * @return 验证结果
     */
    @ResponseBody
    @GetMapping("/getInfo")
    public Map<String, Object> getInfo(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        String token = request.getHeader("accessToken");
        String userId = (String)redisUtil.getWithPrefix(token);
        User user = userService.getById(userId);
        result.put("code", 200);
        result.put("message", "验证成功");
        result.put("data", user);
        return result;
    }
}
