package com.ck.demo.javaGuide;

public class DeadClass {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        new Thread(() -> {
            synchronized (s1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "get s1 want s2");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    synchronized (s2) {
                    }
                }
            }
        },"A").start();
        new Thread(() -> {
            synchronized (s1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "get s2 want s1");
                    Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                synchronized (s2) {
                }
            }
        }
        },"B").start();
    }

}
class Student{

}