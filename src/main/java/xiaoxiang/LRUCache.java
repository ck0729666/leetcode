package xiaoxiang;

/**
import java.util.HashMap;

//最近最少使用
//get通过key获取之后，把此key移在双向链表的最后一个
//删除的话从双向链表的头部开始删除
public class LRUCache {
    private int size;
    private int capacity;
    private HashMap<Integer,Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity,0.75F);
        this.size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        return getNode(key)==null? -1:getNode(key).value;
    }
    private Node getNode(int key){
        Node node = map.get(key);
        if(node==null){
            return null;
        }
        if(node!=tail){
            if(node==head){
                head = head.next;
                head.pre = null;
            }else {
                node.next.pre = node.pre;
                node.pre.next = node.next;
            }
            appendToTail(node);
        }
        return node;
    }
    private void appendToTail(Node node){
        if(head==null){
            head = tail = node;
        }else {
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
    }
    private void trimToSize(){
        while(size>capacity){
            Node pre = head;
            head = head.next;
            head.pre = null;
            map.remove(pre.key);
            size--;
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key,value);
        Node node1 = getNode(key);
        if(node1==null){
            map.put(key, node);
            appendToTail(node);
            size++;
            trimToSize();
        }else {
            node1.value = value;
        }
    }

}

class Node {
    Node pre;
    Node next;
    int key;
    int value;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}*/
