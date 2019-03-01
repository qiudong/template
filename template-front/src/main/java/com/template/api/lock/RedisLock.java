package com.template.api.lock;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLock implements Lock {

    private static final String script = "if redis.call('get', KEYS[1]) == ARGV[1]\n" +
            "    then\n" +
            "        return redis.call('del', KEYS[1])\n" +
            "    else\n" +
            "        return 0\n" +
            "end";

    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L; // 解锁成功标识

    private final String lockKey; // lock Key
    private final long lockExpiryInMsec; // 锁的过期时长，单位毫秒 / 重试屏障时间

    private final RedisTemplate redisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public RedisLock(String lockKey, long lockExpiryInMsec, RedisTemplate redisTemplate) {
        this.lockKey = lockKey;
        this.lockExpiryInMsec = lockExpiryInMsec;
        this.redisTemplate = redisTemplate;
    }

    /***
     * 阻塞式加锁
     * 重试3次
     */
    @Override
    public void lock() {
        if (tryLock()) { // 加锁成功
            return;
        } else {
            try {
                Thread.sleep(lockExpiryInMsec / 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long acquireTime = System.currentTimeMillis();
            long expiryTime = System.currentTimeMillis() + lockExpiryInMsec; // 锁的请求到期时间
            if (expiryTime >= acquireTime) lock(); // 不能无限重试下去 设置重试次数
        }
    }


    // 非阻塞式加锁
    @Override
    public boolean tryLock() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Boolean result =
                redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, lockExpiryInMsec, TimeUnit.MILLISECONDS);
        threadLocal.set(uuid);
        if (result) {
            return true;
        }
        return false;
    }

    // 解锁
    @Override
    public void unlock() {

        boolean unlockResult = redisTemplate.execute((RedisCallback<Object>) connection -> connection.eval(script.getBytes(), ReturnType.INTEGER,
                1, lockKey.getBytes(), threadLocal.get().getBytes())).equals(RELEASE_LOCK_SUCCESS_RESULT);
        if (unlockResult) {
            // 解锁成功
            threadLocal.remove();
        } else {
            // 解锁失败
        }


    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


}
