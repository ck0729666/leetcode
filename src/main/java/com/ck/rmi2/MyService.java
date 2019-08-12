package com.ck.rmi2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyService extends Remote {

    String say(String someOne) throws RemoteException;
}
