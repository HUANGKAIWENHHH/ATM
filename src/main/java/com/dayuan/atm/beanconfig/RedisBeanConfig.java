package com.dayuan.atm.beanconfig;

import com.dayuan.atm.util.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisBeanConfig {

    @Value("${redis.host}")
    private String host;

    //配置
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(4);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(4);
        //获取连接时的最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(3000);
        //在获取连接的时候检查有效性
        jedisPoolConfig.setTestOnBorrow(true);
        //在空闲时检查有效性
        jedisPoolConfig.setTestWhileIdle(true);

        return jedisPoolConfig;
    }

    //创建连接池
    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host);
        return jedisPool;
    }

    @Bean
    public RedisUtils redisUtils(JedisPool jedisPool) {
        RedisUtils redisUtils = new RedisUtils();
        redisUtils.setjedisPool(jedisPool);
        return redisUtils;
    }
}
