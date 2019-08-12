package com.ck.demo.test.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    public  CglibProxy(Object target) {
        this.target = target;
    }

    private Enhancer enhancer = new Enhancer();

    //为目标对象生成代理对象
    public Object getProxy() {
        //设置创建子类的类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类对象代理
        return enhancer.create();
    }
    /**
     * 拦截所有目标类方法的调用
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.print("日志开始");
        //代理类调用父类的方法
        methodProxy.invoke(target, objects);
        System.out.println("日志结束");
        return null;
    }
}
