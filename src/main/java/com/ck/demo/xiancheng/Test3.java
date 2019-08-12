package com.ck.demo.xiancheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Condition接口的await和singalAll配合Lock的lock和unlock使用
public class Test3 {
}
class Queue {
    Lock lock = new ReentrantLock();
    Condition prodCond = lock.newCondition();
    Condition consCond = lock.newCondition();

    final int CAPACITY = 10;
    Object[] container = new Object[CAPACITY];
    int count = 0;
    int putIndex = 0;
    int takeIndex = 0;

    public void putEle(Object ele) throws InterruptedException {
        try {
            lock.lock();
            while (count == CAPACITY) {
                System.out.println("已满，生产者休息");
                prodCond.await();
            }
            container[putIndex] = ele;
            System.out.println("生产元素");
            putIndex++;
            if(putIndex >= CAPACITY) {
                putIndex = 0;
            }
            count++;
            System.out.println("通知消费者去消费");
            consCond.signalAll();
        } finally {
            lock.unlock();
        }
    }
    public Object takeEle() throws InterruptedException {
        try {
            lock.lock();
            while (count == 0) {
                System.out.println("已空，消费者休息");
                consCond.await();
            }
            Object ele = container[takeIndex];
            System.out.println("消费元素");
            takeIndex++;
            if(takeIndex >= CAPACITY) {
                takeIndex = 0;
            }
            count--;
            System.out.println("通知生产者去生产");
            prodCond.signalAll();
            return ele;
        } finally {
            lock.unlock();
        }
    }
}






































