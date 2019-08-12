
package xiaoxiang;


import java.util.*;

public class Test1 {
    //洗牌算法
    //在进行第i次迭代时,元素A[i]时从元素A[i]到A[n]中随机选取的。第i次迭代后，A[i]不再改变。
    public static void fisherYatesShuffle(int[] A){
        int length = A.length;
        int rand = 0;
        int swap = 0;
        Random random = new Random();
        for(int i = length-1;i>0;i--){
            rand = Math.abs(random.nextInt()%(i+1));
            if(rand!=i){
                swap = A[rand];
                A[rand] = A[i];
                A[i] = swap;
            }
        }
    }


















    public static ListNode reverse(ListNode node){
        ListNode pre = null;
        ListNode next = null;
        while (node!=null){
            next = node.next;//备份node.next
            node.next = pre;//更新node.next
            pre = node;//移动pre
            node = next;
        }
        return pre;//返回新链表头
    }
    public static ListNode reverseBetween(ListNode head, int m, int n){
        int changeLength = n-m+1;
        ListNode pre = null;
        ListNode result = head;//转换后的链表头结点，不是特殊情况就是head
        while (head!=null&&(--m>0)){//将head向前移动m-1个位置
            pre = head;//记录head的前驱
            head = head.next;
        }
        ListNode modifyTail = head;//记录逆置后的链表尾
        ListNode newHead = null;
        while(head!=null&&(changeLength-->0)){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        modifyTail.next = head;//连接逆置后的链表尾与逆置端的后一个节点
        if(pre!=null){
            pre.next=newHead;//将逆序前的第一个节点与逆序后的节点连接
        }else{
            result = newHead;
        }
        return result;
    }
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int len1 = listLength(pHead1);
        int len2 = listLength(pHead2);
        if(len1>len2){
            pHead1 = walkNode(pHead1, len1-len2);
        }else{
            pHead2 = walkNode(pHead2, len2-len1);
        }
        while (pHead1!=null){
            if(pHead1==pHead2){
                return pHead1;
            }
            pHead1 = pHead2.next;
            pHead2 = pHead2.next;
        }
        return null;
    }
    private int listLength(ListNode head){
        if(head==null){
            return 0;
        }
        int sum = 1;
        while (head.next!=null){
            sum++;
            head = head.next;
        }
        return sum;
    }
    private ListNode walkNode(ListNode head, int step){
        while (step-->0){
            head = head.next;
        }
        return head;
    }
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!=fast){
            if(fast==null || fast.next==null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if(fast==null || fast.next==null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow){
                break;
            }
        }
        fast = head;
         while (fast!=slow){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    //输入: head = 1->4->3->2->5->2, x = 3
    //输出: 1->2->2->4->3->5
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode moreHead = new ListNode(0);
        ListNode less = lessHead;
        ListNode more = moreHead;
        while (head!=null){
            if(head.val<x){
                less.next = head;
                less = less.next;
            }else{
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        more.next = null;
        less.next = moreHead.next;
        return lessHead.next;
    }






    public static int[] netherlandsPartition(int[] arr, int left, int right, int num) {
        int less = left-1;
        int more = right+1;
        int cur = left;
        while (cur<more){
            if (arr[cur]<num){
                swap(arr,++less,cur++);
            }else if(arr[cur]>num){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1};
        nextGreaterElements(nums);
    }
    public static int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i<length*2;i++){
            if (stack.isEmpty()){
                stack.add(nums[i]);
            }else {
                while (!stack.isEmpty() && stack.peek() <= nums[i % length]) {
                    stack.pop();
                }
                res[i % length] = stack.empty() ? -1 : stack.peek();
            }
        }
        return res;
    }
    public int countDigitOne(int n) {
        if(n==0){
            return 0;
        }
        int curr=0,before=0,next=0;
        int i=1;
        int count = 0;
        while (n/i!=0){
            curr = (n/i)%10;
            before = n/(i*10);
            next = n-(n/i)*i;
            if (curr==0){
                count += before*i;
            }else if(curr==1){
                count += before*i+next+1;
            }else {
                count += (before+1)*i;
            }
            i *= 10;
        }
        return count;
    }









}









class RandomListNode {
    public int val;
    public RandomListNode next;
    public RandomListNode random;

    public RandomListNode() {}

    public RandomListNode(int _val){//,Node _next,Node _random) {
        val = _val;
        //next = _next;
        //random = _random;
    }
}
//复杂链表，一个next指针，一个random指针
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val){//,Node _next,Node _random) {
        val = _val;
        //next = _next;
        //random = _random;
    }
}
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
//双向链表
class Node1 {
    Node1 pre;
    Node1 next;
    int key;
    int value;
    Node1(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


















