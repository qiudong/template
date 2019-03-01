package com.template.api.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/***
 * 多业务模块分布式锁
 */
@Configuration
public class LockConfig {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public RedisLock redisLock() {
        return new RedisLock("lock",300, stringRedisTemplate);
    }

    @Bean
    public RedisLock redisLock1() {
        return new RedisLock("lock1",400, stringRedisTemplate);
    }
}
