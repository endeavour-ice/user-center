package com.user.usercenter.entity.socket_;

import lombok.Data;

/**
 * @author ice
 * @date 2022/7/13 20:33
 *  服务器发送个浏览器的websocket的数据
 */
@Data
public class ResultMessage<T> {
    /**
     * 是否是系统发送的数据
     */
    private boolean isSystem;

    private String fromName;

    private T message;
}
