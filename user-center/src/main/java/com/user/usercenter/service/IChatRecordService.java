package com.user.usercenter.service;

import com.user.usercenter.model.domain.ChatRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 聊天记录表 服务类
 * </p>
 *
 * @author ice
 * @since 2022-07-28
 */
public interface IChatRecordService extends IService<ChatRecord> {

    /**
     *  查询所有的聊天记录
     * @param userId 用户id
     * @param friendId 朋友id
     * @return 集合
     */
    List<ChatRecord> selectAllList(String userId, String friendId);
}
