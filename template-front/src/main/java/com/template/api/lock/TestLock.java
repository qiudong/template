package com.template.api.lock;

import javax.annotation.Resource;

public class TestLock {

    public int count = 100;

    private RedisLock redisLock = new RedisLock();

    public void send() {
        Task task = new Task();

        Thread thread = new Thread(task, "A");
        Thread thread1 = new Thread(task, "B");
        Thread thread2 = new Thread(task, "C");
        Thread thread3 = new Thread(task, "D");
        Thread thread4 = new Thread(task, "E");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    class Task implements Runnable {

        @Override
        public void run() {

            while (count > 0) {
                try {
                    if (!redisLock.tryLock()) {
                        System.out.println("当前排队人数过多，稍后重试~");
                        return;
                    }
                    if (count > 0) {
                        System.out.println(Thread.currentThread().getName() + "售出第" + count-- + "张");
                    }

                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    redisLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {

        new TestLock().send();
    }
}
