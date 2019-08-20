package com.template.con;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Description: java类作用描述
 * @Author: qiudong
 * @CreateDate: 2019-08-19 14:11
 * @UpdateUser: qiudong
 * @UpdateDate: 2019-08-19 14:11
 * @Version: V1.0
 */
public enum State {

    /***
     * 一个与创建但是未启动的线程则处于该状态
     */
    NEW,
    /***
     * 描述： 该状态包含两个子状态  READY RUNNING  当线程正在运行时 状态为RUNNING 调用 yield()可编程ready ready 状态表示 可被线程
     * 调度器调用变为RUNNING状态
     */
    RUNNABLE,
    /***
     * 描述： 处于阻塞状态的线程 不会占用处理器资源
     * 条件： 执行一次阻塞io或者等待锁的释放
     * 释放条件：阻塞式IO执行 完成 获取服务器重新申请到资源
     */
    BLOCKED,
    /***
     * 描述： 等待其他线程执行
     * 条件： 执行一次wait(),join() 函数线程会处于wait状态
     * 释放条件：执行 notify,notifyAll
     */
    WAITTING, //
    /***
     * 与WAITTING 相同，不过是有时间的wait
     */
    TIMED_WARTTING,
    TEMINATED,
}
