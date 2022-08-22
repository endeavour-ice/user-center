package com.user.usercenter.service;

import com.user.usercenter.model.domain.UserFriend;
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
public interface IUserFriendService extends IService<UserFriend> {
    /**
     * 接收好友请求
     * @param reqId
     */
    void addFriendReq(String reqId,String userId);

    List<String> selectFriend(String userId);
}
