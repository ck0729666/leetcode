package ck.mianshi;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\tput 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\tput 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\tput 3");
                blockingQueue.put("3");
            }catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\ttake 1");
                blockingQueue.take();
                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\ttake 2");
                blockingQueue.take();
                try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\ttake 3");
                blockingQueue.take();
            }catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();

    }
}
