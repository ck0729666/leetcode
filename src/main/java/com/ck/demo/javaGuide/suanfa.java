package com.ck.demo.javaGuide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class suanfa {
    public static void main(String[] args) {
        int[][] array = {{3,2,1},{4,3,2},{9,6,5}};
        boolean flag = find(array, 8);
        if(flag) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println(replaceKongge("sss XXX  dd"));
        System.out.println("--------------");
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        p1.next = p2;
        p2.next = p3;
        removeDaoshuK(p1, 2);
        System.out.println(p1.next.val);
        String[] strs1 = {"flower", "flow", "flight"};
        String[] strs2 = {"fa", "fb", "fc"};
        System.out.println(longestCommonPrefix(strs1));
        System.out.println(longestCommonPrefix(strs2));
    }

    //迭代法快
    int feibonaqi(int num) {
        if(num<=0) {
            return 0;
        }
        if(num == 2 || num == 1) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for(int i = 3; i <= num; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
    int feibonaqi2(int num) {
        if(num<=0) {
            return 0;
        }
        if(num == 1) {
            return 1;
        }
        if(num == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for(int i = 3; i <= num; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
    int feibonaqi3(int num) {
        return 1<<--num;
    }
    public static boolean find(int[][] array, int k) {
        int m = array.length-1;
        int n = array[0].length-1;
        while(m>=0&&n>=0) {
            if(array[m][n] == k) {
                return true;
            } else if(array[m][n] < k) {
                n--;
            } else {
                m--;
            }
        }
        return false;
    }
    static String replaceKongge(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
    public double power(double base, int exponent) {
        double result = 1.0;
        for(int i = 0; i < exponent; i++) {
            result *= base;
        }
        if(exponent > 0) {
            return result;
        } else {
            return 1/ result;
        }
    }
    public static int[] reOrder(int[] array) {
        if(array.length == 0 || array.length == 1) {
            return array;
        }
        int oddCount = 0, oddBegin = 0;
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            if((array[i]&1) == 1) {
                oddCount++;
            }
        }
        for(int i = 0; i < array.length; i++) {
            if((array[i]&1) == 1) {
                newArray[oddBegin++] = array[i];
            } else {
                newArray[oddCount++] = array[i];
            }
        }
        for(int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
        return array;
    }
    public ListNode daoshuK(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int flag = k;
        int count = 0;
        while(p1!=null) {
            p1 = p1.next;
            count++;
            if(k < 1){
                p2 = p2.next;
            }
            k--;
        }
        if(count < k) {
            return null;
        }
        return p2;
    }
    public ListNode reverse(ListNode head) {
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
    public ListNode mergeLianbiao(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1.val < list2.val) {
            list1.next = mergeLianbiao(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeLianbiao(list1, list2.next);
            return list2;
        }
    }
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void push(int a) {
        stack1.push(a);
    }
    public int pop() {
        if (stack1.empty() && stack2.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        while (stack2.empty() && !stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /**
     * 思路：遍历入栈队列，用一个栈来存储入栈元素，出栈队列与栈中元素比较
     * @param pushA
     * @param popA
     * @return
     */
    public boolean isOrder(int[] pushA, int[] popA) {
        if(pushA.length==0 || popA.length==0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int popCount = 0;
        for(int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while(!stack.empty() && popA[popCount]== stack.peek()) {
                stack.pop();
                popCount++;
            }
        }
        return stack.empty();
    }

    /**--------------------------------上方12道题分割线-------------------------------*/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while(p!=null || q!=null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(p != null) {
                p = p.next;
            }
            if(q != null) {
                q = q.next;
            }
        }
        if(carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    public static ListNode removeDaoshuK(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = head;
        ListNode p2 = head;
        int count = 0;
        int flag = k;
        while(p1.next != null) {
            p1 = p1.next;
            count++;
            if(k < 1) {
                p2 = p2.next;
            }
            k--;
        }
        if(count < k) {
            return null;
        }
        p2.next = p2.next.next;
        return dummy.next;
    }

    /**https://zhuanlan.zhihu.com/p/43824258*/

    //3
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null) {
            return null;
        }
        Arrays.sort(strs);
        char[] chars1 = strs[0].toCharArray();
        char[] chars2 = strs[strs.length-1].toCharArray();
        int length = strs[0].length() < strs[strs.length-1].length() ? strs[0].length() : strs[strs.length-1].length();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(count<length){
            if(chars1[count]==chars2[count]) {
             sb.append(chars1[count]);
             count++;
            } else {
                break;
            }
        }
        return sb.toString();
    }
    //4
    public static int longestHuiwen(String s) {
        HashSet<Character> hs = new HashSet<>();
        char[] chars = s.toCharArray();
        int count = 0;
        int length = s.length();
        if(length == 0) {
            return 0;
        }
        for(int i = 0; i < length; i++) {
            if(hs.contains(chars[i])) {
                hs.remove(chars[i]);
                count++;
            } else {
                hs.add(chars[i]);
            }
        }
        return hs.isEmpty() ? count*2 : count*2+1;
    }
    //5
    public boolean isHuiwen(String s) {
        if(s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while(l < r) {
            if(!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if(!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                if(Character.toLowerCase(s.charAt(l))!=Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
    public int longestHuiwenXulie(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = len-1; i>=0;i--) {
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++) {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else  {
                        dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                    }
                }
            }
        return dp[0][len-1];
    }
    //哈希表+2指针
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[] count = new int[128];
        if(l1>l2) {
            return false;
        }
        for(int i = 0; i < l1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if(allZero(count)) {
            return true;
        }
        for(int i = l1; i < l2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i-l1) - 'a']++;
            if(allZero(count)) {
                return true;
            }
        }
        return false;
    }
    public boolean allZero(int[] count) {
        int l = count.length;
        for(int i = 0; i < l; i++) {
            if(count[i]!=0) {
                return false;
            }
        }
        return true;
    }
    ArrayList<String> res = new ArrayList<>();
    public void Helper(char[] str, int i) {
        if(i == str.length-1) {
            res.add(String.valueOf(str));
        } else {
            for(int j = i; j < str.length; j++) {
                if(j!=i&&str[i]==str[j]) {
                    continue;
                }
                swap(str, i, j);
                Helper(str, i+1);
                swap(str, i, j);
            }
        }
    }
    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }


}
class ListNode{
    ListNode next = null;
    int val;
    public ListNode(int val) {
        this.val = val;
    }
}







































