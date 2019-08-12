package com.ck.demo.xiancheng;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class dingshiTask {

    /**
     * 通过线程池执行->定时任务
     * 可以灵活的设置第一次执行任务delay时间
     * @param args
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("xx");
            }
        };
        ScheduledExecutorService ser = Executors.newSingleThreadScheduledExecutor();
        //第二个参数为首次执行的delay时间，第三个参数为定时执行的间隔时间
         //创建并执行在给定延迟后启用的 ScheduledFuture。
         //        参数：
         //        callable - 要执行的功能
         //        delay - 从现在开始延迟执行的时间
         //        unit - 延迟参数的时间单位
         //        返回：
         //        可用于提取结果或取消的 ScheduledFuture
        ser.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
    }
    //通过自旋操作减少CPU切换以及恢复现场导致的消耗












}
