package ck.mianshi;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws Exception{

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask, "AA");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "------------");
        int result1= 19;
        int result2 = futureTask.get();
        System.out.println(result1+result2);
    }
}
class MyThread implements Runnable{
    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        //System.out.println("Callable进入了FutureTask");
        try{ TimeUnit.SECONDS.sleep(3); }catch(Exception e) {e.printStackTrace();}
        return 1024;
    }
}
