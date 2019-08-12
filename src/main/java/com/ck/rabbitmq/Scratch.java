package com.ck.rabbitmq;

public class Scratch {

    public static void main(String[] args) {
        // 实际中会有 nameserver 服务来找到 broker 具体位置以及 broker 主从信息
        Broker broker = new Broker();
        Producer producer1 = new Producer();
        producer1.connectBroker(broker);
        Producer producer2 = new Producer();
        producer2.connectBroker(broker);

        producer1.hashCode();
        Consumer consumer1 = new Consumer();
        consumer1.connectBroker(broker);
        Consumer consumer2 = new Consumer();
        consumer2.connectBroker(broker);

        for(int i = 0; i < 2; i++) {
            producer1.asyncSendMsg("producer1 send msg" + i);
            producer2.asyncSendMsg("producer2 send msg" + i);
        }
        System.out.println("broker has msg:" + broker.getAllMagByDisk());

        for (int i = 0; i < 1; i++) {
            System.out.println("consumer1 consume msg：" + consumer1.syncPullMsg());
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("consumer2 consume msg：" + consumer2.syncPullMsg());
        }
    }
}
