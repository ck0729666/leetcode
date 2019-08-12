package com.ck.rmi2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

public class ServerMain{
    public static void main(String[] args) throws Exception{
        //注册服务
        LocateRegistry.createRegistry(8866);
        //指定通信端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());
        //创建服务
        MyService myService = new MyServiceImpl();
        //注册
        Naming.bind("rmi://localhost:8866/myService",myService);
        System.out.println("启动正常");
    }
}
