package com.ck.rmi2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * 这里我们还需要一个针对服务端的配置类，因为RMI的通信端口是随机产生的，
 * 因此有可能会被防火墙拦截。为了防止被防火墙拦截，
 * 需要强制制定RMI的通信端口，一般通过自定义一个RMISocketFactory类来实现。
 *
 */
public class CustomerSocketFactory extends RMISocketFactory {

    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if(port == 0) {
            port = 8885;
        }
        System.out.println("RMI 通信端口：" + port);
        return new ServerSocket(port);
    }
}
