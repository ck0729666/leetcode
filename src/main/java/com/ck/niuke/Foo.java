package com.ck.niuke;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
        while (number!=1) {
            c1.await();
        }
        // printFirst.run() outputs "firvxst". Do not change or remove this line.
             printFirst.run();
            number = 2;
        c2.signal();
        }catch (Exception e){
        e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            number = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
lock.lock();
try {
    while (number!=3){
        c3.await();
    }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

}catch (Exception e){
e.printStackTrace();
}finally {
    lock.unlock();
}
    }

}