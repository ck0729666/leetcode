package ck.mianshi;

import org.omg.CORBA.TIMEOUT;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁应该是读读共享，读写独占，写写独占
 * 所以用传统的ReentrantLock不能满足要求（一致性得到保证，并发性不行）
 * 采用ReetrantReadWriteLock
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"", tempInt+"");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                }catch(Exception e) {e.printStackTrace();}
            }, String.valueOf(i)).start();
        }

        for(int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                }catch(Exception e) {e.printStackTrace();}
            }, String.valueOf(i)).start();
        }
    }
}
class MyCache {
    private volatile HashMap<String ,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                map.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成： " + result);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}