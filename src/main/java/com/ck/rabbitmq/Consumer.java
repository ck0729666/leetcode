package com.ck.rabbitmq;

public class Consumer {
    private Broker broker;

    public void connectBroker(Broker broker) {
        this.broker = broker;
    }
    public String syncPullMsg() {
        return broker.getMsg();
    }
}
