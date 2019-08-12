package com.ck.rmi2;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args) throws Exception{
        //服务引入
        MyService myService = (MyService) Naming.lookup("rmi://localhost:8866/myService");
        System.out.println("RMI返回 " + myService.say("mySelf"));
    }
}
