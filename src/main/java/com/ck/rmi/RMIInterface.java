package com.ck.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 让在某个 Java 虚拟机上的对象调用另一个 Java 虚拟机中的对象上的方法
 *
 */
public interface RMIInterface extends Remote {
    public String test() throws RemoteException;
}
