package com.user.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.usercenter.mapper.TeamMapper;

import com.user.usercenter.model.domain.Team;
import com.user.usercenter.service.TeamService;

import org.springframework.stereotype.Service;

/**
* @author BING
* @description 针对表【team(队伍表)】的数据库操作Service实现
* @createDate 2022-08-22 15:45:11
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




