package com.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title GlobalExceptionHandler
 * <p> @Description 全局异常捕获
 *
 * @author ACGkaka
 * @date 2021/4/30 17:27
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义异常，返回json信息
     *
     * @param myException 自定义异常
     * @return 错误信息JSON
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public Map<String, Object> errorHandle(RuntimeException myException) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", myException.getMessage());
        return result;
    }

    /**
     * 全局异常捕捉处理
     *
     * @param exception 系统异常
     * @return 错误信息JSON
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> errorHandler(Exception exception) {
        exception.printStackTrace();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", exception.getMessage());
        return result;
    }
}
