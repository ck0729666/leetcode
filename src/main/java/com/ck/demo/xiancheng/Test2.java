package com.ck.demo.xiancheng;

import java.util.concurrent.CyclicBarrier;

import static jdk.nashorn.internal.objects.Global.println;

public class Test2 {
    static  final int COUNT = 5;
    static CyclicBarrier cd = new CyclicBarrier(COUNT, new Singer());

    public static void main(String[] args) {
        for(int i = 0; i < COUNT; i++) {
            new Thread(new Staff(i, cd)).start();
        }
    }
}

class Singer implements Runnable {
    @Override
    public void run() {
        println("唱歌！");
    }
}

class Staff implements Runnable {

    CyclicBarrier cb;
    int num;

    Staff(int num, CyclicBarrier cb) {
        this.num = num;
        this.cb = cb;
    }

    @Override
    public void run() {
        println("员工(%d)出发。。。", num);
        //doingLongTime();
        println("员工(%d)到达地点一。。。", num);
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("员工(%d)再出发。。。", num);
        //doingLongTime();
        println("员工(%d)到达地点二。。。", num);
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("员工(%d)再出发。。。", num);
        //doingLongTime();
        println("员工(%d)到达地点三。。。", num);
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("员工(%d)结束。。。", num);
    }

}