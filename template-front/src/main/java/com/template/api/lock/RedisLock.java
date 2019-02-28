package com.template.api.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
public class RedisLock implements Lock {

    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public RedisLock() {
        System.out.println("11");
    }

    /***
     * 阻塞式加锁
     */
    @Override
    public void lock() {
        if (tryLock()) { // 加锁成功
            return;
        } else {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    // 非阻塞式加锁
    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Boolean result = false;
        try {
            result = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        threadLocal.set(uuid);
        if (result) {
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    // 解锁
    @Override
    public void unlock() {
        String script = fileRead();
        redisTemplate.execute((RedisCallback<Object>) connection -> connection.eval(script.getBytes(), ReturnType.INTEGER,
                1, "lock".getBytes(), threadLocal.get().getBytes()));

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public String fileRead() {
        File file = new File("/Users/qiudong/myproject/template/template-front/src/main/java/com/template/api/lock/unlock.lua");//定义一个file对象，用来初始化FileReader
        FileReader reader = null;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while (true) {
            try {
                if (!((s = bReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
        }
        try {
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = sb.toString();
        return str;
    }

}
