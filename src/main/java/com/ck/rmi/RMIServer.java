package com.ck.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        try {
            RMIInterface rml = new RMIImpl();
            LocateRegistry.createRegistry(9999);
            Naming.bind("rmi://localhost:9999/rml", rml);
        }
         catch (Exception e) {

         }
    }
}

