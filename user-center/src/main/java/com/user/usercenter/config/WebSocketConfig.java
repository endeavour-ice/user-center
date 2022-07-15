package com.user.usercenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ice
 * @date 2022/7/14 10:48
 */
@Configuration
public class WebSocketConfig {

    // 注入 ServerEndpointExporter 对象 扫描 ServerEndpoint 注解 bean
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
