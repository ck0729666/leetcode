package ck.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

//ABA问题的解决
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100 ,1);
    static int beginTemp = atomicStampedReference.getStamp();

    public static void main(String[] args) {

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ABA问题的解决");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("第一次版本号：" + stamp);
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp+1);
            int stamp2=  atomicStampedReference.getStamp();
            System.out.println("第二次版本号：" + stamp2);
            atomicStampedReference.compareAndSet(101, 100, stamp2, stamp2+1);
            System.out.println("第三次版本号：" + atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            Boolean flag = atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), 2019, beginTemp, beginTemp + 1);
            System.out.println("当前变量值： " + atomicStampedReference.getReference() + "  修改是否成功？" + flag);
            System.out.println(atomicStampedReference.getStamp());
        }, " t4").start();





    }
































}
