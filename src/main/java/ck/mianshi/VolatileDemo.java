package ck.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *1.验证volatile的可见性
 *1.1没加volatile
 *1.2添加volatile，可以保证可见性问题
 *
 *2.
 *2.1验证volatile不保证原子性
 *2.2为什么？写覆盖
 *2.3如何解决？
 *  +sync
 *  AtomicInteger
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for(int i = 0; i < 20; i++) {
            new Thread(() ->{
                for(int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面的2-个线程全计算完成，再用main线程获得最终结果值看是多少
        //剩下main和gc线程
        while(Thread.activeCount() > 2) {
        Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally automicInteger value: " + myData.atomicInteger);
    }




    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        //AAA线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTO60();
            System.out.print(Thread.currentThread().getName() + "\t updated number value: " + myData.number);
        }, "AAA").start();
        //main线程
        while(myData.number == 0) {

        }
        System.out.print(Thread.currentThread().getName() + "\t mission is over");
    }
}
class MyData { //MyData.java -> MyData.class
    volatile int number = 0;
    public void addTO60(){
        this.number = 60;
    }
    //此number前面加了volatile
    public void addPlusPlus() {
        number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}
