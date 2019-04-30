package com.dayuan.atm.text;

import com.alibaba.fastjson.JSON;
import com.dayuan.atm.entity.User;
import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("203.195.161.135");


        //System.out.println(jedis.ping());

        //jedis.set("key", "name");
        //jedis.get("user");
        System.out.println(jedis.keys("*"));
        //System.out.println(jedis.del("user"));
        //System.out.println(jedis.expire());

        //
        //User user = new User();
        //user.setUsername("jobs");
        //user.setPassword("123456");
        //user.setId(2000);
        //
        //jedis.set("user:2000", JSON.toJSONString(user));
        //
        //String result = jedis.get("user:2000");
        //System.out.println(result);
        //
        //User user2 = JSON.parseObject(result, User.class);
        //System.out.println(user2.getId() + user2.getUsername() + user2.getPassword());


    }
}