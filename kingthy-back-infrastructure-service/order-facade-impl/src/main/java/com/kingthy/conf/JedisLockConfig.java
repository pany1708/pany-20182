package com.kingthy.conf;

import com.kingthy.util.JedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * JedisLock(描述其作用)
 * <p>
 * 赵生辉 2017年11月23日 14:08
 *
 * @version 1.0.0
 */
@Component
@Configuration
public class JedisLockConfig
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public JedisLock jedisLock(){
        JedisLock jedisLock = new JedisLock(stringRedisTemplate, "kingThyLock:generateSn");

        return jedisLock;
    }
}
