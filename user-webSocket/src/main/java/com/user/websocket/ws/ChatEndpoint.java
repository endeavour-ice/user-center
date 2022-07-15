package com.user.websocket.ws;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ice
 * @date 2022/7/14 10:27
 */
@ServerEndpoint("/chat")
@Component
public class ChatEndpoint {


    // 用来存储每一个客户端对应的 ChatEndpoint 对象
    private static final Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    // 声明session 对象 ,通过该对象可以发送消息给指定的客户端
    private Session session;

    // 声明一个 httpSession对象 储存了之前在httpSession 存储的对象
    private HttpSession httpSession;
    /**
     *  连接建立时被调用
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 将局部 session 赋值给成员session
        this.session = session;
        // 获取httpSession 的对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        //
        Object userLoginState = httpSession.getAttribute("userLoginState");

    }

    /**
     *  接收到客服端发送的数据是被调用
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session) {

    }

    /**
     * 连接时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {

    }
}
