package com.ck.demo.test.proxy2;

import org.junit.jupiter.api.Test;

public class Test2 {

    @Test
    public void testDynamicProxy() {
        IUserDao target = new UserDao();
        System.out.println(target.getClass());
        IUserDao proxy = (IUserDao)new ProxyFactory1(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
