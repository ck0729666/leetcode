package ck.mianshi;

import com.sun.tools.corba.se.idl.ExceptionEntry;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo2 {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try{
                myResource.myProd();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try{
                myResource.myConsumer();
                System.out.println();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try{TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("5S到，活动结束");
        myResource.stop();
    }
}
class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while(FLAG) {
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "拜拜");
            }
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println(Thread.currentThread().getName() + "\t老板叫停");
    }
    public void myConsumer() throws Exception {
        String result= null;
        while(FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒没有取到蛋糕，退出！");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.print(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }
    public void stop() throws Exception {
        this.FLAG = false;
    }






























}
