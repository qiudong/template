package com.template.api.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 测试redis 类
 * @Author: qiudong
 * @CreateDate: 2019-04-01 10:57
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-04-01 10:57
 * @Version: V1.0
 */
@Component
public class TestRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String query() {
        return redisTemplate.opsForValue().get("mykey");
    }

    public void hsetTest() {
        User user = new User();
        user.setAge(22);
        user.setName("qqq");
        String json = JSONObject.toJSONString(user);

        Map<String, String> userMap = new HashMap<>();
        userMap.put("hash2", json);
        user.setAge(33);
        user.setName("wwwww");
        String json1 = JSONObject.toJSONString(user);
        userMap.put("hash3", json1);


        redisTemplate.opsForHash().putAll("myhashkey", userMap);

    }

    public User hgetTest() {
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        String json = redisTemplate.opsForHash().values("myhashkey").toString();
        Map<Object, Object> myhashkey = redisTemplate.opsForHash().entries("myhashkey");

        final Map<String, User> userMap = myhashkey.entrySet().stream()
                .collect(Collectors.toMap(m -> m.getKey().toString(),
                        entry -> JSONObject.parseObject(entry.getValue().toString(), User.class)));

//        User user = JSONObject.parseObject(json, User.class);
        List<User> parseArray = JSONObject.parseArray(json, User.class);
        final User user = parseArray.get(0);
        return user;

    }


    public String setStr() {
        return "";
    }

    public void getList() {
    }


}
