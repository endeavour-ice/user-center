package com.user.websocket.ws;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;

/**
 * @author ice
 * @date 2022/7/14 11:01
 */

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        // 将httpseesion 存储到配置对象中
        Map<String, Object> userProperties = sec.getUserProperties();
        userProperties.put(session.getClass().getName(), session);
    }
}
