package com.template.con;

import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-08-19 11:50
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-08-19 11:50
 * @Version: V1.0
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
