package com.ck.rmi;

import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try{
            RMIInterface rmi = (RMIInterface) Naming.lookup("rmi://localhost:9999/rml");
            System.out.println(rmi.test());
            //System.out.println("ss");
        } catch (Exception e) {


        }
    }
}
