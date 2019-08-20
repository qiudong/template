package com.template.con;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-08-20 16:30
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-08-20 16:30
 * @Version: V1.0
 */
public class LockTest {

    private static int count = 0;

    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {
            LockDemo lockDemo = new LockDemo();
            lockDemo.run();
        }

        countDownLatch.await();

    }

    static class LockDemo implements Runnable{

        @Override
        public void run() {
            countDownLatch.countDown();
            System.out.println(count++);
        }
    }
}
