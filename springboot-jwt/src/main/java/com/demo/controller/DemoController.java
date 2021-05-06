package com.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.entity.User;
import com.demo.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
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
@RequestMapping("index")
public class DemoController {

    /**
     * 登录
     *
     * @param user 用户名/密码
     * @return Token
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        // TODO 登录业务
        user = new User(1, "ACGkaka", "123456", "123@123.com");

        String token = TokenUtil.createToken(user);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", token);
        return result;
    }

    /**
     * 验证登录
     *
     * @return 验证结果
     */
    @ResponseBody
    @GetMapping("/verifyLogin")
    public Map<String, Object> verifyLogin(HttpServletRequest request) {

        String token = request.getHeader("accessToken");
        boolean isValid = TokenUtil.isValid(token);
        Map<String, Object> result = new HashMap<>();
        if(isValid) {
            result.put("code", 200);
            result.put("message", "验证成功");
        } else {
            result.put("code", 400);
            result.put("message", "验证失败");
        }
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

        String token = request.getHeader("accessToken");
        Map<String, Object> data = TokenUtil.getClaims(token);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "验证成功");
        result.put("data", data);
        return result;
    }
}
