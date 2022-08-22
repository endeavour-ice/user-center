package com.user.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.usercenter.service.UserTeamService;
import com.user.usercenter.model.domain.UserTeam;
import com.user.usercenter.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author BING
* @description 针对表【user_team(队伍表)】的数据库操作Service实现
* @createDate 2022-08-22 15:55:33
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




