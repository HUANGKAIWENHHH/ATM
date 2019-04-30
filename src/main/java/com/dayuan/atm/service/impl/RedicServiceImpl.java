package com.dayuan.atm.service.impl;

import com.alibaba.fastjson.JSON;
import com.dayuan.atm.DTO.CardDTO;
import com.dayuan.atm.service.RedicService;
import com.dayuan.atm.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;

@Service
public class RedicServiceImpl implements RedicService {

    @Autowired
    private RedisUtils redisUtils;

    //因为是缓存，所以可能key名字重复，所以要设置唯一的key
    //key格式：项目：模块：功能：参数
    @Override
    public String getKey(String module, String function, String parm) {
        return "atm" + ":" + module + ":" +function + ":" + parm;
    }

    //放入缓存
    @Override
    public void cacheCard(List<CardDTO> cardDTOS, int userId) {
        String key = getKey("card", "myCard", String.valueOf(userId));

        //这样就不用close
        try (Jedis jedis = redisUtils.getJedis()) {
            //cardDTOS是对象，通过json转换为String
            jedis.setex(key, 60, JSON.toJSONString(cardDTOS));
        }
    }

    //取出缓存
    @Override
    public List<CardDTO> getCard(int userId) {
        String key = getKey("card", "myCard", String.valueOf(userId));

        try (Jedis jedis = redisUtils.getJedis()) {
            String cards = jedis.get(key);

            //如果为空直接返回空的集合，集合不要用null返回
            //Collections集合工具类
            if (StringUtils.isBlank(cards)) {
                return Collections.EMPTY_LIST;
            }

            //缓存里的是String类型，通过json转换成数组返回
            return JSON.parseArray(cards, CardDTO.class);
        }

    }

    //让缓存失效
    @Override
    public void expire(String key , int second) {
        try (Jedis jedis = redisUtils.getJedis()) {
            jedis.expire(key, second);
        }
    }
}
