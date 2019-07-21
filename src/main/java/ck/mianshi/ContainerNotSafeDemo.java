package ck.mianshi;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for(int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
    //set,map一样有此问题
    public static void listNotSafe() {
        //List<String> list1 = new ArrayList<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //list.forEach(System.out::println);
        List<String> list = new CopyOnWriteArrayList<>();

        for(int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * 1.异常：java.util.ConcurrentModificationException
         *
         * 2.导致原因
         *      并发争抢导致，参考我们的花名册签名情况。
         *      一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常。并发修改异常。
         * 3.解决方案
         *      Vector
         *      Collections.synchronized(new ArrayList<>());
         *      new CopyOnWriteArrayList<>()
         * 4.优化建议
         *
         *
         */}


}
