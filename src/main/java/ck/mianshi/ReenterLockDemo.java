package ck.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            }catch (Exception e) {

            }
        }, "t1").start();
        try {
            phone.sendSMS();
        }catch (Exception e) {

        }
        new Thread(() -> {

        }, "t2").start();


        try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}

        System.out.println();

        new Thread(() -> {
            phone.get();
        }, "t3").start();
        new Thread(() -> {
            phone.get();
        }, "t4").start();



    }
}

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoke sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t #######invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get() {
        lock.lock();
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoke get()");
            getTwo();
        } finally {
            lock.unlock();
            lock.unlock();
            lock.unlock();
        }
    }
    public void getTwo() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoke getTwo()");
        } finally {
            lock.unlock();
        }
    }
}