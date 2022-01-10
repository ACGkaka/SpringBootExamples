package com.demo.controller;

import com.demo.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title WebSocketController
 * <p> @Description websocket controller
 *
 * @author ACGkaka
 * @date 2022/1/10 10:13
 */
@Slf4j
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    /**
     * 本地socket测试
     */
    @GetMapping("/socketTest")
    public String socketTest() {
        return "socketTest";
    }

    /**
     * 页面请求
     */
    @GetMapping("/view/{socketId}")
    public String socketView(@PathVariable String socketId, Model model) {
        if (socketId == null || socketId.trim().length() == 0) {
            return "socketFail";
        }
        model.addAttribute("socketId", socketId);
        return "socketView";
    }

    /**
     * 推送数据接口
     */
    @GetMapping("/push/{socketId}")
    public Map<String, Object> pushToMap(@PathVariable String socketId, String message) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 推送数据
            WebSocketServer.sendInfo(message, socketId);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            // 抛出异常
            log.error(e.getMessage(), e);
            result.put("code", 500);
            result.put("msg", "推送数据失败：" + e.getMessage());
        }
        return result;
    }
}
