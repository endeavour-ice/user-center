package com.user.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.usercenter.entity.ChatRecord;
import com.user.usercenter.mapper.ChatRecordMapper;
import com.user.usercenter.service.IChatRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author ice
 * @since 2022-07-28
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {
    @Override
    @Transactional
    public List<ChatRecord> selectAllList(String userId, String friendId) {

        QueryWrapper<ChatRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).or().eq("user_id", friendId);
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setHasRead(1);
        baseMapper.update(chatRecord, wrapper);
        List<ChatRecord> chatRecords = baseMapper.selectList(wrapper);
        if (chatRecords.size() <= 0) {
            throw new RuntimeException("查无记录");
        }
        return chatRecords;
    }
}
