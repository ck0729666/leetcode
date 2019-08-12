package com.ck.rabbitmq;

import java.util.concurrent.LinkedBlockingDeque;

public class Broker {
    private LinkedBlockingDeque<String> messageQueue = new LinkedBlockingDeque<>(Integer.MAX_VALUE);

    public void sendMsg(String msg) {
        try{
            messageQueue.put(msg);
        }catch (Exception e) {

        }
    }
    public String getMsg() {
        try{
            return messageQueue.take();
        }catch (Exception e) {

        }
        return null;
    }
    public String getAllMagByDisk() {
        StringBuilder sb = new StringBuilder("\n");
        messageQueue.iterator().forEachRemaining((msg) -> {sb.append(msg + "\n");});
        return sb.toString();
    }
}
