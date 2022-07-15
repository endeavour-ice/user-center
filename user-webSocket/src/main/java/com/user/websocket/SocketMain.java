package com.user.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ice
 * @date 2022/7/13 20:30
 */
@SpringBootApplication
@ComponentScan("com.user")
public class SocketMain {
    public static void main(String[] args) {
        SpringApplication.run(SocketMain.class, args);
    }
}
