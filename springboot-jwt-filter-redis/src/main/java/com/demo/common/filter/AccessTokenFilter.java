package com.demo.common.filter;

import com.demo.common.exception.BaseException;
import com.demo.user.service.UserService;
import com.demo.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p> @Title AccessTokenFilter
 * <p> @Description JWT过滤器
 *
 * @author ACGkaka
 * @date 2021/6/16 18:09
 */
@Component
public class AccessTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if ("/index/login".equals(req.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            String accessToken = req.getHeader("accessToken");
            if (accessToken != null) {
                // 依赖获取
                WebApplicationContext applicationContext = WebApplicationContextUtils
                        .getRequiredWebApplicationContext(request.getServletContext());
                RedisUtil redisUtil = applicationContext.getBean(RedisUtil.class);
                UserService userService = applicationContext.getBean(UserService.class);

                // 判断Token有效性
                String userId = redisUtil.getWithPrefix(accessToken);
                if (userId != null && userService.getById(userId) != null) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new BaseException(400, "登录超时");
                }
            } else {
                throw new BaseException(400, "Token不能为空");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
