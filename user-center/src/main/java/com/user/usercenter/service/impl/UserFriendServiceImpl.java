package com.user.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.usercenter.entity.UserFriend;
import com.user.usercenter.mapper.UserFriendMapper;
import com.user.usercenter.service.IUserFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ice
 * @since 2022-07-28
 */
@Service
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements IUserFriendService {
    @Override
    @Transactional
    public void addFriendReq(String reqId,String userId) {

        UserFriend userFriend = new UserFriend();
        userFriend.setUserId(userId);
        userFriend.setFriendsId(reqId);
        int insert = baseMapper.insert(userFriend);
        if (insert <= 0) {
            throw new RuntimeException("添加好友失败");
        }
    }

    @Override
    public List<String> selectFriend(String userId) {
        QueryWrapper<UserFriend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).or().eq("friends_id", userId);
        List<UserFriend> userFriends = baseMapper.selectList(wrapper);
        List<String> userIdByList = new ArrayList<>();
        userFriends.forEach(userFriend -> {
            if (userFriend.getUserId().equals(userId)) {
                userIdByList.add(userFriend.getFriendsId());
            }
            if (userFriend.getFriendsId().equals(userId)) {
                userIdByList.add(userFriend.getUserId());
            }
        });
        return userIdByList;
    }
}
