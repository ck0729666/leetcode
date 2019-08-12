package com.ck.demo.shejimoshi;

public class danli {
    public static void main(String[] args) {

        Single.getInstance().doSomething();

    }
    private danli() {}
    private volatile static danli instance;

    synchronized  public static danli getInstance() {
        if(instance == null) {
            instance = new danli();
        }
        return instance;
    }

    public static danli getInstance11() {
        if(instance == null) {

            synchronized (danli.class){
                if(instance == null) {
                    instance = new danli();
                }
            }
        }
        return instance;
    }
    public static danli getInstance333() {
        if(instance == null) {
            synchronized (danli.class) {
                if(instance == null) {
                    instance = new danli();
                }
            }
        }
        return instance;
    }



























}
