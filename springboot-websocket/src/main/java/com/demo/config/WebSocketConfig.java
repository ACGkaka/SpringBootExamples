package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p> @Title WebSocketConfig
 * <p> @Description Websocket 配置类
 *
 * @author ACGkaka
 * @date 2022/1/10 10:00
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndPointExporter() {
        return new ServerEndpointExporter();
    }
}
