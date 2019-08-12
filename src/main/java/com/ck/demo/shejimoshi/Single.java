package com.ck.demo.shejimoshi;
import java.io.ObjectStreamException;
import java.io.Serializable;

//枚举模式的单例
public enum Single implements MySingleton{
    INSTANCE {
        @Override
        public void doSomething() {
            System.out.println("complete singleton");
        }
    };
    public static MySingleton getInstance() {
        return Single.INSTANCE;
    }

}

interface MySingleton {
    void doSomething();
}

class MyObject implements Serializable {
    private static final long serialVersionUID = 888L;
    private static class MyObjectHandler {
        private static final MyObject myobject = new MyObject();
    }
    private MyObject() {}
    public static MyObject getInstance() {
        return MyObjectHandler.myobject;
    }
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("redsResolve");
        return MyObjectHandler.myobject;
    }
















}