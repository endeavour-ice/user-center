package com.user.usercenter.listener;

import com.rabbitmq.client.Channel;
import com.user.usercenter.config.mq.MqClient;
import com.user.usercenter.model.domain.ChatRecord;
import com.user.usercenter.service.IChatRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ice
 * @date 2022/8/20 16:08
 */
@Component
@Slf4j
public class NettyListenerMQ {
    @Autowired
    private IChatRecordService chatRecordService;

    @RabbitListener(queues = MqClient.NETTY_QUEUE)
    public void SaveChatRecord(Message message, Channel channel, ChatRecord chatRecord) {
        try {
            if (chatRecord != null) {
                boolean save = chatRecordService.save(chatRecord);
                if (!save) {
                    log.error("保存聊天记录失败");
                }
            }else {
                log.error("保存聊天记录失败");

            }
        } catch (Exception e) {
            log.error("保存聊天记录失败" + e.getMessage());
        }

    }
}
