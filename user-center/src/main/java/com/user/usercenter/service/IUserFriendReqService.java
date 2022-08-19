package com.user.usercenter.service;

import com.user.usercenter.entity.User;
import com.user.usercenter.entity.UserFriendReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ice
 * @since 2022-07-28
 */
public interface IUserFriendReqService extends IService<UserFriendReq> {
    int sendRequest(String fromUserId, String toUserId);

    List<User> checkFriend(String userId);

    int Reject(String id);
}
