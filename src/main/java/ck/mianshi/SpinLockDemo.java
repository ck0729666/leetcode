package ck.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//手写个自旋锁
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "AA").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.lock();
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "BB").start();
    }
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\tcome in");
        /**
        do{

        }while (!atomicReference.compareAndSet(null, thread));*/
        while(!atomicReference.compareAndSet(null, thread)) {

        }
    }
    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\tquit");
    }
}
