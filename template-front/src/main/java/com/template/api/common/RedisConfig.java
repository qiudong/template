package com.template.api.common;

import redis.clients.jedis.Jedis;

public class RedisConfig {


    public static Jedis getJdeis(){
        Jedis jedis = new Jedis("localhost",6379);
        return jedis;
    }


}
