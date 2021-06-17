package com.demo.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.demo.user.service.UserService;
import com.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p> @Title TokenInterceptor
 * <p> @Description Token 验证拦截器
 *
 * @author ACGkaka
 * @date 2021/5/6 14:01
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("accessToken");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (token == null) {
            throw new RuntimeException("accessToken不存在，请重新登录");
        }
        // 验证 token
        if (TokenUtil.isValid(token)) {
            return true;
        } else {
            throw new RuntimeException("Token验证失败，请重新登录");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
