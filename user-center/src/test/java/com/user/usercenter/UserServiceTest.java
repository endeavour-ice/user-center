package com.user.usercenter;

import com.user.usercenter.entity.User;
import com.user.usercenter.service.IUserService;
import com.user.usercenter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author ice
 * @date 2022/7/13 11:33
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;
    @Test
    public void  TestSearchUserTag() {
        List<String> list = Arrays.asList("python");
        List<User> userList = userService.searchUserTag(list);
        for (User user : userList) {
            System.out.println("===================================");
            System.out.println(user);
        }
    }
}
