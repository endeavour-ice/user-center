package com.user.usercenter.netty;

import com.google.gson.Gson;

import com.user.usercenter.entity.ChatRecord;
import com.user.usercenter.service.IChatRecordService;
import com.user.usercenter.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author ice
 * @date 2022/7/22 16:27
 */

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用来保存所有的客服端连接
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 时间格式器
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 当接收到数据后自动调用
        String message = msg.text();
        Gson gson = new Gson();
        Message mess = gson.fromJson(message, Message.class);
        System.out.println(mess+"mess=======================================");
        IChatRecordService recordService = SpringUtil.getBean(IChatRecordService.class);
        switch (mess.getType().toString()) {
            case "0":
                // 建立用户与通道的关联
                String userId = mess.getChatRecord().getUserId();
                UserChannelMap.put(userId, ctx.channel());
                System.out.println("建立用户 :" + userId + "与通道的关联: " + ctx.channel().id());
                UserChannelMap.print();
                break;
            // 处理客服端发送消息
            case "1":
                // 将聊天消息保存到数据库
                ChatRecord chatRecord = mess.getChatRecord();
                System.out.println(chatRecord+"chatRecord=======================================");
                // 发送消息好友在线,可以直接发送消息给好友
                Channel channel= UserChannelMap.getFriendChannel(chatRecord.getFriendId());
                if (channel != null) {
                    chatRecord.setHasRead(1);
                    recordService.save(chatRecord);
                    channel.writeAndFlush(new TextWebSocketFrame(gson.toJson(mess)));
                }else {
                    chatRecord.setHasRead(0);
                    recordService.save(chatRecord);
                    // 不在线,暂时不发送
                    System.out.println("用户 "+chatRecord.getFriendId() +"不在线!!!!");
                }
                break;
            case "3":
                System.out.println("接收心跳消息: "+ message);
                break;

        }

    }

    // 新的客服端连接时调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());
    }

    // 出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("出现异常,关闭连接");
        cause.printStackTrace();
        // 通道 出现异常 移除该通道
        String channelId = ctx.channel().id().asLongText();
        UserChannelMap.removeByChannelId(channelId);
    }
    // 用户断开连接调用
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // 通道断开连接时 移除该通道
        System.out.println("关闭连接");
        String channelId = ctx.channel().id().asLongText();
        UserChannelMap.removeByChannelId(channelId);
    }
}
