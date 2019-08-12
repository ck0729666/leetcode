package com.ck.demo.test.proxy;

public class CglibProxyTest {

    public static void main(String[] args) {
        Train train = new Train();
        CglibProxy proxy = new CglibProxy(train);
        Train t = (Train)proxy.getProxy();
        t.move();
    }

}
