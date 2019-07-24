package ck.mianshi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue {
}
class myResource{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while(FLAG) {
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue) {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println(Thread.currentThread().getName()+"stop!");
    }
    public void myConsumer() throws Exception {
        String result = null;
        while(FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")) {
                FLAG =false;
                System.out.println(Thread.currentThread().getName()+"\t超过两秒没有取到蛋糕，退出！");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
    }
    public void stop() throws Exception {
        this.FLAG = false;
    }
 }
