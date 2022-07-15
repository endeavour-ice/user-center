package com.user.websocket.entity;

import lombok.Data;

/**
 * @author ice
 * @date 2022/7/13 20:32
 * 浏览器发送给服务器的数据
 */
@Data
public class Message {
    private String toName;
    private String message;
}
