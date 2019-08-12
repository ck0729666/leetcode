package com.ck.demo.test;

import java.util.Stack;

public class Test {

    int Fibonacci(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
    int Fibonacci_1(int number ) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        return Fibonacci_1(number - 2) + Fibonacci_1(number - 1);
    }
    int jumpFloor (int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for(int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return  third;
    }
    int jumpFloor_1(int number) {
        return 1 << --number;
    }
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1;//行
        int column = 0;//列
        while(row >= 0 || column <= array[0].length) {
            if(target < array[row][column]) {
                row--;
            }else if (target > array[row][column]) {
                column++;
            }else {
                return  true;
            }
        }
        return false;
    }
    void orderTiaozheng(int[] array){
        if(array.length == 0 || array.length == 1) {
            return;
        }
        int[] newArray = new int[array.length];
        int oddCount = 0, oddBegin = 0;
        for(int i = 0; i < array.length; i++) {
            if((array[i]&1) == 1) {
                oddCount++;
            }
        }
        for(int i = 0; i < array.length; i++) {
            if((array[i]&1)==1) {
                newArray[oddBegin++] = array[i];
            } else {
                newArray[oddCount++] = array[i];
            }
        }
        for(int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }

    }

    static ListNode findReverseK(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int count = 0;
        int flag = k;
        while(p1.next != null) {
            p1 = p1.next;
            count++;
            if(k <= 1) {
                p2 = p2.next;
            }
            k--;
        }
        if(count < flag) {
            return null;
        }
        return p2;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        System.out.println(Test.findReverseK(a, 3).val);
    }

    public ListNode ReverseList(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public ListNode merge(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1.val <= list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        }else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }
    public boolean isPopOrder(int[] pushA, int[] popA) {
        if(pushA.length == 0 || popA.length == 0) {
            return false;
        }
        Stack<Integer> s = new Stack<Integer>();
        int popIndex = 0;
        for(int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);
            while(!s.empty() && s.peek() == popA[popIndex]) {
                s.pop();
                popIndex++;
            }
        }
        return s.empty();
    }
    public boolean isPopOrder_1(int[] pushA, int[] popA) {
        if(pushA.length == 0 || popA.length == 0) {
            return  false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int popIndex = 0;
        for(int i = 0; i< pushA.length; i++) {
            stack.push(pushA[i]);
            if(!stack.empty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }




















}
class ListNode{
    ListNode next = null;
    int val;
    ListNode(int val) {
        this.val = val;
    }
}

class Solution1{
    Stack<Integer> stack1 = new Stack();
    Stack<Integer> stack2 = new Stack();

    public void push(int a) {
        stack1.push(a);
    }
    public Integer pop() {
        if(stack2.empty() && stack1.empty()) {
            throw new RuntimeException("empty!");
        }
        if(stack2.empty()) {
            while(!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }




















}
