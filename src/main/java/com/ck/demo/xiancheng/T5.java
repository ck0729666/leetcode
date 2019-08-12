package com.ck.demo.xiancheng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法 add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */

//CyclicBarrier：与CountdowLatch很像，它也能实现线程之间的等待，还有很多更强大的功能：
    //让一组线程到达屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有呗阻塞的线程才会继续干活。
    //每个线程调用await（）告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
public class T5 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Arr<Object> arr = new Arr<Object>(latch);
        new Thread(new R2(latch)).start();
        new Thread(new R1(arr)).start();
    }
}
class Arr<E> {
    private CountDownLatch latch;
    private List<E> arr = new ArrayList<E>();
    public Arr(CountDownLatch latch) {
        this.latch = latch;
    }
    public synchronized boolean add(E e) {
        boolean v = arr.add(e);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if(5 == size()) {
            latch.countDown();
        }
        System.out.println("====" + size());
        return v;
    }
    public synchronized int size() {
        return arr.size();
    }

}
class R1 implements Runnable {
    private Arr<Object> arr;
    public R1(Arr<Object> arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            arr.add(new Object());
        }
    }
}
class R2 implements Runnable {
    private CountDownLatch latch;
    public R2(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("now......over");
    }
}

















