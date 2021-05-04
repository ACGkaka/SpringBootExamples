package com.demo.module.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title GlobalExceptionHandler
 * <p> @Description 全局异常捕获
 *
 * @author ACGkaka
 * @date 2021/4/30 17:27
 */
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    /**
     * 捕获自定义异常，返回json信息
     * @param req HttpServletRequest
     * @param exception 自定义异常
     * @return 异常信息
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public Map<String, Object> errorHandle(HttpServletRequest req, RuntimeException exception) {
        return initResultMap(req.getRequestURI(), 500, exception);
    }

    /**
     * 捕获系统异常
     *
     * @param exception 系统异常
     * @return 异常信息
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Map<String, Object> errorHandle(HttpServletRequest req, Exception exception) {
        exception.printStackTrace();
        return initResultMap(req.getRequestURI(), 500, exception);
    }

    /**
     * 初始化返回Map
     *
     * @param url 请求地址
     * @param code 状态码
     * @param exception 异常
     * @return Map
     */
    private Map<String, Object> initResultMap(String url, int code, Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("exception",exception);
        map.put("message", exception.getMessage());
        map.put("url", url);
        return map;
    }
}