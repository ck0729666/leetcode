/**
package com.ck.demo.test;

import java.util.HashMap;


 * 定义一个双向链表，包含k-v
 * LRU包含头尾节点、容量、个数、hashmap，构造函数包含capacity和loadSize
 * appendTail(Node node)先判断size是否为0，为0的话则头尾都为node；不为0的话则加到尾部
 * getNode(int key)先判断map中是否存在这个key的node，没有返回空；有且为尾节点的话直接返回；不为尾节点的话判断是否为头结点，判断后调用appendTail(Node node)
 * get()调用getNode()得到value
 * trimToSize()减少size
 * put(int key, int value)
 *

public class LRU {
    private int size;
    private int capacity;
    private HashMap<Integer, Node> hashMap;
    private Node head;
    private Node tail;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        hashMap = new HashMap<>(capacity, 0.75F);
    }

    public void appendTail(Node node) {
        if(size == 0) {
            head = tail = node;
        } else {
            node.pre = tail;
            tail.next = node;
            node.next = null;
            tail = node;
        }
    }

    public int get(int key) {
        Node node = getNode(key);
        return node==null ? -1 : node.value;
    }

    public Node getNode(int key) {
        Node node = hashMap.get(key);
        if(node == null) {
            return null;
        }
        if(node != tail) {
            if(node == head) {
                head = head.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            appendTail(node);
        }
        return node;
    }

    public void trimToSize() {
        while (size > capacity) {
            Node pre = head;
            head = head.next;
            head.pre = null;
            size--;
            hashMap.remove(pre.key);
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        Node pre = getNode(key);
        if(pre == null) {
            hashMap.put(key, node);
            appendTail(node);
            size++;
            trimToSize();
        } else {
            pre.value = value;
        }
    }

}

//定义双向链表，构造函数包含k——v
class Node {
    Node pre;
    Node next;
    int key;
    int value;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}




*/
















