package com.ck.demo.test;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Solution1111 {

    public static void main(String[] args) {
        //a 97 A 65 0 48
        System.out.println();
    }

    //AbstractQueuedSynchronizer

    /**
     * leetcode：91：解码方法
     * @return
     */
    /**
    public int jiemaNum(String s) {
        //dp[i]用于记录字符串到第i-1位前的解码方法的总数
        int[] dp = new int[s.length()+1];
        dp[0] = 1;

        for(int i = 1; i < s.length()+1; i++) {

        }
    }*/



    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第k小元素，而不是第k个元素
     */
    public int kFind11(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int left = matrix[0][0], right = matrix[m-1][n-1];
        while(left <= right) {
            int mid = left + (right-left)/2;
            int cnt = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n && matrix[i][j] <= mid;j++) {
                    cnt++;
                }
            }
            if(cnt < k) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }





    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
     * 则重建二叉树并返回。
     * @param pre
     * @param in
     * @return
     *
     *
     * 递归思想，每次将左右两颗子树当成新的子树进行处理，
     * 中序的左右子树索引很好找，前序的开始结束索引通过计算中序中左右子树的大小来计算，
     * 然后递归求解，直到startPre>endPre||startIn>endIn说明子树整理完到。
     * 方法每次返回左子树活右子树的根节点
     */
    public TreeNode reTree(int[] pre, int[] in) {
        TreeNode root = reBinaryTree(pre, 0, pre.length-1, in, 0, in.length-1);
        return root;
    }
    private TreeNode reBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn){
        if(startPre>endPre || startIn>endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++) {
            if(in[i]==pre[startPre]) {
                root.left = reBinaryTree(pre, startPre+1, startPre+i-startIn, in, startIn, i-1);
                root.right = reBinaryTree(pre, i-startIn+startPre+1, endPre, in, i+1, endIn);
            }
        }
        return root;
    }

    public TreeNode rereTr(int[] pre, int[] in) {
        TreeNode root = reBTr(pre, 0, pre.length-1, in, 0, in.length-1);
        return root;
    }
    public TreeNode reBTr(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn){
        if(startPre>endPre || startIn>endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startIn]);

        for(int i = startIn; i <= endIn; i++) {
            if(in[i]==pre[startPre]){
                root.left = reBTr(pre, startPre+1, startPre+i-startIn, in, startIn, i-1);
                root.right = reBTr(pre, i-startIn+startPre+1, endPre, in, i+1, endIn);
            }
        }
        return root;
    }


    public int min11(int[] array) {
        if(array.length == 0) {
            return 0;
        }
        int min = array[0];
        for(int i = 0; i < array.length-1; i++) {
            if(array[i] > array[i+1]) {
                return array[i+1];
            }
        }
        return min;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转,输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return
     */
    public int min333(int[] array) {
        if(array.length == 0) {
            return 0;
        }
        int low = 0;
        int high = array.length-1;
        while(low<high) {
            int mid = low + (high-low)/2;
            if(array[mid] > array[high]) {
                low = mid + 1;
            }else if(array[mid] == array[high]) {
                high = high - 1;
            }else {
                high = mid;
            }
        }
        return array[low];
    }

    /**
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int count = 0, max = 0, i;
        for(i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == '('){
                count++;
            }else {
                count--;
            }
            //上次循环保存的最大值
            max = Math.max(max, count);
        }
        scanner.close();
        System.out.println(max);
        System.out.println("---------");
    }
     */
    public int firstSimple(String str) {
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {
            if(!bs1.get(c) && !bs2.get(c)) {
                bs1.set(c);
            }else if(bs1.get(c) && !bs2.get(c)) {
                bs2.set(c);
            }
        }
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(bs1.get(c) && bs2.get(c)) {
                return i;
            }
        }
        return -1;
    }
    public ListNode find22(ListNode head1, ListNode head2) {
        ListNode l1 = head1, l2 = head2;
        while(l1 != l2) {
            l1 = (l1 == null) ? head2 : l1.next;
            l2 = (l2 == null) ? head1 : l2.next;
        }
        return l1;
    }
    private PriorityQueue<Integer> left = new PriorityQueue<Integer>((o1, o2) -> o2-o1);
    private PriorityQueue<Integer> right = new PriorityQueue<Integer>();
    private int N = 0;
    public void Insert(Integer val) {
        if(N%2==0) {
            left.add(val);
            right.add(left.poll());
        } else {
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }
    public Double GetMain() {
        if(N % 2 == 0) {
            return (left.peek()+right.peek()) / 2.0;
        } else {
            return (double)right.peek();
        }
    }
    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while(!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
    //求连续最大的和
    public int find5(int[] nums) {
        if(nums.length==0 || nums==null) {
            return 0;
        }
       int maxZhi = Integer.MIN_VALUE;
       int sum = 0;
       for(int num : nums) {
           sum =  sum<=0 ? num : sum+num;
           maxZhi = Math.max(maxZhi, sum);
       }

       return maxZhi;
    }
    public String printMin(int[] numbers) {
        if(numbers == null || numbers.length == 0) {
            return "";
        }
        int n = numbers.length;
        String[] nums = new String[n];
        for(int i = 0; i < n; i++){
            nums[i] = numbers[i] + "";
        }
        //此方法传入T[],和一个比较器
        Arrays.sort(nums, (s1, s2) ->(s1+s2).compareTo(s2+s1));
        String ret = "";
        for (String str : nums) {
            ret += str;
        }
        return ret;
    }
    //用两个栈，第一个存放push的，然后minStack如果为空，就把push的放进去
    //如果不为空，就比较这次push的和minStack里面存在的哪个小，就push进去哪个
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty()?node:Math.min(minStack.peek(), node));
    }
    public  void pop() {
        dataStack.pop();
        minStack.pop();
    }
    public int top() {
        return dataStack.peek();
    }
    public int min() {
        return minStack.peek();
    }
    public boolean isOrder(int[] pushA, int[] popA) {
        int n = pushA.length;
        Stack<Integer> stack = new Stack<>();
        for(int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushA[pushIndex]);
            while(popIndex < n && !stack.isEmpty() && stack.peek()==popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    public boolean IsPopOrder(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty()
                    && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    public ArrayList<Integer> printFromToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int cnt = queue.size();
            while(cnt-- > 0) {
                TreeNode t = queue.poll();
                if(t == null) {
                    continue;
                }
                list.add(t.val);
                queue.add(root.left);
                queue.add(root.right);
            }
        }
        return list;
    }
    //用队列来进行层次遍历
    public ArrayList<ArrayList<Integer>> print22(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while(cnt-- >0) {
                TreeNode t = queue.poll();
                if(t == null) {
                    continue;
                }
                list.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
            if(list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }
    //按照之字打印
    public ArrayList<ArrayList<Integer>> Print(TreeNode root)  {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while(!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while(cnt-- > 0) {
                TreeNode node = queue.poll();
                if(node == null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if(reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            if(list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }


    /**
    链接：https://www.nowcoder.com/questionTerminal/a861533d45854474ac791d90e447bafd
    来源：牛客网

    public class Solution {
        public boolean VerifySquenceOfBST(int [] sequence) {
            if(sequence.length==0)
                return false;
            if(sequence.length==1)
                return true;
            return ju(sequence, 0, sequence.length-1);

        }

        public boolean ju(int[] a,int star,int root){
            if(star>=root)
                return true;
            int i = root;
            //从后面开始找
            while(i>star&&a[i-1]>a[root])
                i--;//找到比根小的坐标
            //从前面开始找 star到i-1应该比根小
            for(int j = star;j<i-1;j++)
                if(a[j]>a[root])
                    return false;;
            return ju(a,star,i-1)&&ju(a, i, root-1);
        }
    }*/







































}
class Solution3 {
    int index = 0;
    TreeNode kNode(TreeNode root, int k) {
        if(root != null) {//中序遍历寻找第k个
            TreeNode node = kNode(root.left, k);
            if(node != null) {
                return node;
            }
            index++;
            if(index == k) {
                return root;
            }
            node = kNode(root.right, k);
            if(node != null) {
                return node;
            }
        }
        return null;
    }
}
class Solution4 {
    int count;
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);

    public void Insert(Integer num) {
        count++;
        if((count & 1) == 0) {//判断偶数的高效写法
            if(!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        } else {
            if(!minHeap.isEmpty() && num > minHeap.peek()) {
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }
}
//33题：二叉搜索树的后序遍历序列
class Solution222 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0){
            return false;
        }
        if(sequence.length==1){
            return true;
        }
        return verify(sequence, 0, sequence.length-1);
    }
    public boolean verify(int[] a, int first, int last){
        if(first>=last){
            return true;
        }
        int i = first;
        while(a[i] < a[last]){
            i++;
        }
        for(int j = i; j<last;j++){
            if(a[j]<a[last]){
                return false;
            }
        }
        return verify(a, i, last-1) && verify(a, first, i-1);
    }

    //34题：二叉树中和为某一值的路径
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if(root == null) {
            return listAll;
        }
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null) {
            //不重新new的话从始至终listAll中所有引用都指向了同一个一个list.....
            listAll.add(new ArrayList<Integer>(list));
        }
        findPath(root.left, target);
        findPath(root.right, target);
        //移除最后一个元素啊，深度遍历完一条路径后要回退
        list.remove(list.size()-1);
        return listAll;
    }











}
//35题.复杂链表的复制
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }

    /**
    public RandomListNode Clone (RandomListNode head) {
        if(head == null) {
            return null;
        }
        //1.在每个节点后面复制节点
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        //2.建立random连接
        cur = head;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if(cur.random != null) {
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
        //3.拆分

    }*/

    //36题：将二叉搜索树转化成一个排序的双向链表
    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode Convert(TreeNode root) {
        inOrder(root);
        return head;
    }
    private void inOrder(TreeNode node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        if(head == null) {
            pre = node;
            head = node;
        } else {
            //把根节点放在链表的右边，然后将head右移，这样得到升序链表
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        inOrder(node.right);
    }
    public static String replaceSpace(StringBuffer str) {
        int length = str.length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char b = str.charAt(i);
            //valueOf(char c): 返回 char 参数的字符串表示形式。
            if(String.valueOf(b).equals(" ")) {
                result.append("%20");
            }else {
                result.append(b);
            }
        }
        return result.toString();
    }
    //将char转化为String的大致方法，6种：
    //效率最高的1.
    String s1 = String.valueOf('c');

    String s2 = String.valueOf(new char[]{'c'});

    String s3 = Character.toString('c');

    String s4 = new Character('c').toString();

    String s5 = "" + 'c';

    String s6 = new String(new char[]{'c'});

    public static String replace(String[] strs) {
        int len = strs.length;
        if(strs.length == 0 || strs == null) {
            return "";
        }
        Arrays.sort(strs);
        int m = strs[0].length();
        int n = strs[len-1].length();
        int nums = Math.min(m, n);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums; i++) {
            if(strs[0].charAt(i)==strs[len-1].charAt(i)) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
    //最长回文串
    //思路：首先把字符串转化为字符数组，然后遍历该数组，判断对应字符是否存在hashSet中，若不在就加进去，若在就让count++，然后移出。
    public int longest(String s) {
        if(s.length() == 0) {
            return 0;
        }
        //用于存放字符
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(!hashSet.contains(chars[i])) {
                hashSet.add(chars[i]);
            } else {
                hashSet.remove(chars[i]);
                count++;
            }
        }
        //return hashSet.isEmpty() ? s.length()/2 : s.length()/2 + 1;
        return hashSet.isEmpty() ? count*2 : count*2+1;
    }
    //验证回文串
    public boolean isHuiwenchuan(String s) {
        if(s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while(l < r) {
            if(!Character.isLetterOrDigit(s.charAt(l))) {   //字符不是字母和数字的情况
                l++;
            }else if(!Character.isLetterOrDigit(s.charAt(r))) {   //字符不是字母和数字的情况
                r--;
            } else {
                //判断二者是否相等
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
    //网易
    public void touru(int x) {
        StringBuilder sb = new StringBuilder();
        while(x >= 1) {
            //偶数的情况
            if((x % 2) == 0){
                x = (x - 2) / 2;
                sb.append("2");
            } else{
                //奇数的情况
                x = (x - 1) / 2;
                sb.append("1");
            }
        }
        System.out.println(sb.reverse());
    }
    public void reverseShu(String s) {
        int number = Integer.parseInt(s);
        int number2 = Integer.parseInt(new StringBuilder(s).reverse().toString());
        System.out.print(number+number2);
    }
    public void changdu(String s) {
        float count = 1;
        for(int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) != s.charAt(i+1)) {
                count++;
            }
        }
        System.out.print(s.length() / count);
    }
    public void test1(int n) {
        StringBuilder sb = new StringBuilder();
        while(n >= 1) {
            if(n%2==0) {
                n = (n-2)/2;
                sb.append("2");
            } else  {
                n = (n-1)/2;
                sb.append("1");
            }
        }
        System.out.print(sb.reverse());
    }
    public void test2(String n) {
        int number1 = Integer.parseInt(n);
        int number2 = Integer.parseInt(new StringBuilder(n).reverse().toString());
        System.out.print(number1+number2);
    }
    public int test3(String str) {
        int count = 1;
        for(int i = 0; i < str.length()-1; i++) {
            if(str.charAt(i)!=str.charAt(i+1)) {
                count++;
            }
        }
        return str.length()/count;
    }

    private TreeNode pre1 = null;
    private TreeNode head1 = null;
    public TreeNode Convert11(TreeNode root) {
        inOrder1(root);
        return head1;
    }
    private void inOrder1(TreeNode node) {
        if(node == null) {
            return;
        }
        inOrder1(node.left);
        if(head == null) {
            pre1 = node;
            head1 = node;
        } else {
            pre1.right = node;
            node.left = pre1;
            pre = node;
        }
        inOrder1(node.right);

    }

    /**
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int value = stack2.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return value;
    }*/
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        int val = stack2.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return val;
    }

    /**
     * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     *
     * 递归版
     * 解题思路：
     * 1.将左子树构造成双链表，并返回链表头节点。
     * 2.定位至左子树双链表最后一个节点。
     * 3.如果左子树链表不为空的话，将当前root追加到左子树链表。
     * 4.将右子树构造成双链表，并返回链表头节点。
     * 5.如果右子树链表不为空的话，将该链表追加到root节点之后。
     * 6.根据左子树链表是否为空确定返回的节点。
     * @param root
     * @return
     */
    public TreeNode Convert3434(TreeNode root) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null) {
            return root;
        }
        //1.
        TreeNode left = Convert3434(root.left);
        TreeNode p = left;
        //2.
        while(p!=null && p.right!=null) {
            p = p.right;
        }
        //3.
        if(left!=null) {
            p.right = root;
        }
        //4.
        TreeNode right = Convert3434(root.right);
        //5.
        if(right!=null) {
            right.left = root;
            root.right = right;
        }
        return left!=null?left : root;
    }
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res=new int[len];
        Arrays.fill(res,-1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len*2; i++){
            while (!stack.isEmpty()&&nums[stack.peek()]<nums[i%len]){
                res[stack.pop()] = nums[i%len];
            }
            if (i<len){
                stack.push(i);
            }
        }
        return res;
    }

    /**
     * 让小朋友们围成一个大圈。然后，随机指定一个数 m，让编号为 0 的小朋友开始报数。每次喊到 m-1 的那个小朋友要出列唱首歌，然后可以在礼品箱中任意的挑选礼物，
     * 并且不再回到圈中，从他的下一个小朋友开始，继续 0...m-1 报数 .... 这样下去 .... 直到剩下最后一个小朋友，可以不用表演。
     * @param n
     * @param m
     * @return
     */
    public int lastShu(int n, int m) {
        if(n == 0) {
            return -1;
        }
        if(n == 1) {
            return 0;
        }

        return (lastShu(n-1, m) + m) % n;
    }
}

    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
    }











     int len = nums.length;
     int[] res=new int[len];
     Arrays.fill(res,-1);
     Stack<Integer> stack = new Stack<>();
     for (int i = 0; i < len*2; i++){
     while (!stack.isEmpty()&&nums[stack.peek()]<nums[i%len]){
     res[stack.pop()] = i%len;
     }
     if (i<len){
     stack.push(i);
     }
     }
     return res;

**/








































































