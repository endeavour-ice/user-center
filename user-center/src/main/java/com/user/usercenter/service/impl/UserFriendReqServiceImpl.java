package com.user.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.usercenter.common.ErrorCode;
import com.user.usercenter.entity.User;
import com.user.usercenter.entity.UserFriendReq;
import com.user.usercenter.exception.GlobalException;
import com.user.usercenter.mapper.UserFriendReqMapper;
import com.user.usercenter.service.IUserFriendReqService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.usercenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ice
 * @since 2022-07-28
 */
@Service
public class UserFriendReqServiceImpl extends ServiceImpl<UserFriendReqMapper, UserFriendReq> implements IUserFriendReqService {
    @Autowired
    private IUserService userService;

    @Override
    public int sendRequest(String fromUserId, String toUserId) {
        QueryWrapper<UserFriendReq> wrapper = new QueryWrapper<>();
        wrapper.eq("from_userid", fromUserId);
        wrapper.eq("to_userid", toUserId);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GlobalException(ErrorCode.NULL_ERROR);
        }
        UserFriendReq userFriendReq = new UserFriendReq();

        userFriendReq.setFromUserid(fromUserId);
        userFriendReq.setToUserid(toUserId);
        return baseMapper.insert(userFriendReq);
    }

    @Override
    public List<User> checkFriend(String userId) {
        QueryWrapper<UserFriendReq> wrapper = new QueryWrapper<>();
        wrapper.eq("to_userid", userId);
        List<UserFriendReq> friendReqList = baseMapper.selectList(wrapper);
        friendReqList= friendReqList.stream().filter(userFriendReq -> userFriendReq.getUserStatus() == 0).collect(Collectors.toList());
        if (friendReqList.isEmpty()) {
            return null;
        }

        ArrayList<String> list = new ArrayList<>();
        for (UserFriendReq userFriendReq : friendReqList) {
            String fromUserid = userFriendReq.getFromUserid();
            list.add(fromUserid);
        }
        List<User> users = userService.listByIds(list);
        if (users.isEmpty()) {
            throw new RuntimeException("查找申请的用户为空");
        }
        return users.stream().peek(user -> userService.getSafetyUser(user)).collect(Collectors.toList());

    }

    @Override
    public int Reject(String id) {
        UserFriendReq userFriendReq = new UserFriendReq();
        QueryWrapper<UserFriendReq> wrapper = new QueryWrapper<>();
        userFriendReq.setUserStatus(1);
        wrapper.eq("from_userid", id);
        return baseMapper.update(userFriendReq, wrapper);
    }
}
