package com.ck.demo.spring.aop;

import org.junit.jupiter.api.Test;

public class SimpleAOPTest {
    @Test
    public void getProxy() throws Exception {
        //1.创建一个MethodInvocation实现类
        MethodInvocation logTask = () -> System.out.println("log tack start");
        HelloServiceImpl helloService = new HelloServiceImpl();
        //2.创建一个Advice
        Advice beforeAdvice = new BeforeAdvice(helloService, logTask);
        //3.为目标对象生成代理
        HelloService helloServiceProxy = (HelloService)SimpleAOP.getProxy(helloService, beforeAdvice);
        helloServiceProxy.sayHelloWorld();
    }
}
