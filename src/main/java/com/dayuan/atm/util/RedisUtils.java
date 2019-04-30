package com.dayuan.atm.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    public JedisPool jedisPool;
    //
    //static {
    //    init();
    //}

    //创建连接池
    //public static void init() {
    //    jedisPool = new JedisPool(new JedisPoolConfig(), "203.195.161.135");
    //}

    //从连接池里获取Jedis
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public JedisPool setjedisPool(JedisPool jedisPool) {
        return this.jedisPool = jedisPool;
    }

}
