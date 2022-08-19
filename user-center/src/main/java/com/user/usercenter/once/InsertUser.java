package com.user.usercenter.once;
import java.time.LocalDateTime;

import com.user.usercenter.entity.User;
import com.user.usercenter.mapper.UserMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ice
 * @date 2022/7/21 18:35
 */
@Component
public class InsertUser {

    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入数据
     */
//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("==========================================");
        final int INSERT_NUM = 100000000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假用户"+i);
            user.setUserAccount("假ice");
            user.setGender("男");
            user.setPassword("12345678");
            user.setTags("");
            user.setProfile("假");
            user.setTel("110");
            user.setEmail("111@qq.com");
            user.setPlanetCode("1111");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
