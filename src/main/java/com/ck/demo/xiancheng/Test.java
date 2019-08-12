package com.ck.demo.xiancheng;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class Test {
    static final int COUNT = 20;
    static CountDownLatch cdl = new CountDownLatch(COUNT);

    public static void main(String[] args) throws Exception{
        new Thread(new Teacher(cdl)).start();
        sleep(1);
        for(int i = 0; i < COUNT; i++) {
            new Thread(new Student(i, cdl)).start();
        }
        synchronized (Test.class) {
            Test.class.wait();
        }
    }
}

class Student implements Runnable {
    CountDownLatch cdl;
    int num;

    Student(int num, CountDownLatch cdl) {
        this.num = num;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("学生" + num + "写卷子");
        System.out.println("学生" + num + "交卷子");
        //在每个线程完成后调用countdown（）
        cdl.countDown();
    }
}
class Teacher implements Runnable {
    CountDownLatch cdl;
    Teacher(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("老师发卷子");
        try {
            //保证调用await（）的线程被堵塞，直到工作线程完成
            //如果我们没有调用 await() 方法，我们将无法保证线程执行的顺序，因此测试会随机失败
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("老师收卷子");
    }
}















