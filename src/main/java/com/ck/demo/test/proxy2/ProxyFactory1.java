package com.ck.demo.test.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
public class ProxyFactory1 {

    //维护一个目标对象
    private Object target;

    public ProxyFactory1(Object target) {
        this.target = target;
    }
    //为目标对象生成代理对象
    public Object getProxyInstance() {
        //类加载器，接口，处理器
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务");
                //执行目标对象方法
                Object returnValue = method.invoke(target, args);
                System.out.println("关闭事务");
                return null;
            }
        });
    }
}*/

public class ProxyFactory1 {

    private Object target;

    public ProxyFactory1(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = method.invoke(target, args);
                return null;
            }
        });
    }
}











