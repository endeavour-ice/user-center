package com.user.usercenter.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ice
 * @date 2022/8/20 16:22
 */
@Configuration
@ConfigurationProperties("spring.redis")
@Data
public class RedissonConfig {
    private String password;
    private String host;
    private String port;

    @Bean
    public RedissonClient redissonClient() {
        // 1. Create config object
        Config config = new Config();
        String redisAddress = String.format("redis://%s:%s", host, port);
        config.useSingleServer().setDatabase(3).setPassword(password).setAddress(redisAddress);
        return Redisson.create(config);
    }
}
