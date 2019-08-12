package com.ck.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIImpl extends UnicastRemoteObject implements RMIInterface {
    public RMIImpl() throws RemoteException{}
    @Override
    public String test() throws RemoteException {
        return "Test";
    }
}