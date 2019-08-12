package com.ck.niuke;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.*;

public class Test {


    private ArrayList<Integer> nums;

    //回文数
    //思路：所有负数都false，x%10和x/1递归出口是反转后的数>前面的数，最后考虑奇偶
    public boolean isPalindrome(int x) {
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reversedNum = 0;
        while (x > reversedNum) {
            reversedNum = reversedNum * 10 + x % 10;
            x = x / 10;
        }
        return x == reversedNum || x == reversedNum / 10;
    }

    //输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
    ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    /**
     * 作者：Monotone
     * 链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6
     * 来源：牛客网
     * <p>
     * 递归思想，每次将左右两颗子树当成新的子树进行处理，中序的左右子树索引很好找，
     * 前序的开始结束索引通过计算中序中左右子树的大小来计算，
     * 然后递归求解，直到startPre>endPre||startIn>endIn说明子树整理完到。
     * 方法每次返回左子树和右子树的根节点
     *
     * @param
     * @return
     */

    public TreeNode reConstructBinaryTree11(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree11(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    private TreeNode reConstructBinaryTree11(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree11(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree11(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }


    //根据前序遍历和中序遍历 重建二叉树
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length != in.length) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {

        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, i + startPre - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i + startPre - startIn + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }

    public static int count(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length - 1; i++) {
            set.add(array[i]);
        }
        return set.size();
    }

    ArrayList<String> res = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str == null) {
            return res;
        }
        PermutationHelper(str.toCharArray(), 0);
        Collections.sort(res);
        return res;
    }

    public void PermutationHelper(char[] str, int i) {
        if (i == str.length - 1) {
            res.add(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                if (j != i && str[i] == str[j]) {
                    continue;
                }
                swap(str, i, j);
                PermutationHelper(str, i + 1);
                swap(str, i, j);
            }
        }
    }

    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        int len = array.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        int num = array[len / 2];
        for (int i = 0; i < len; i++) {
            if (array[i] == num) {
                count++;
            }
        }
        if (count <= len / 2) {
            return 0;
        }
        return num;
    }

    public int MoreThanHalfNum_Solution11(int[] array) {
        int len = array.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(array);
        int num = array[len / 2];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (num == array[i]) {
                count++;
            }
        }
        if (count < len / 2) {
            return 0;
        }
        return num;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k <= 0 || k > input.length) {
            return list;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> {
            return o2 - o1;
        });
        for (int i = 0; i < input.length; i++) {
            if (i < k) {
                maxHeap.offer(input[i]);
            } else if (input[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }
        for (Integer m : maxHeap) {
            list.add(m);
        }
        return list;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int max = array[0];
        int res = array[0];
        for (int i = 0; i < array.length; i++) {
            max = Math.max(array[i], res + array[i]);
            res = Math.max(max, res);
        }
        return res;
    }

    public String PrintMinNumber(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        /**
         *
         * 关于比较器，比如例题中的{3，32，321} 数组中先放入3，
         * 而后3和32比较，因为332>323 所以3>32 数组此时为[32,3];
         * 再往数组中加入321，先与32比较，32132<32321 故 321<32
         * 故321应排在32前面，再与3比较 3213<3321 故321<3 数组最终排序[321，32，3]
         */
        Collections.sort(list, (o1, o2) -> {
            String s1 = o1 + "" + o2;
            String s2 = o2 + "" + o1;
            return s1.compareTo(s2);
        });
        for (Integer l : list) {
            sb.append(l);
        }
        return sb.toString();
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = (p1 == null) ? p2 : p1.next;
            p2 = (p2 == null) ? p1 : p2.next;
        }
        return p1;
    }

    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m][n];
        int maxLen = 0;//, maxEnd = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i]==B[j]) {
                    if (i==0||j==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1]+1;
                    }
                    if (maxLen<dp[i][j]){
                        maxLen = dp[i][j];
                        //maxEnd = i;
                    }
                }
            }
        }
        return maxLen;
    }

    public String longestPalindrome1(String s) {
        int len = s.length();
        if (s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s);
        String reverse = sb.reverse().toString();
        int[][] dp = new int[len][len];
        int maxEnd = 0;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (s.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    maxEnd = i;
                }
            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) {return s.isEmpty();}
        boolean first_match = (!s.isEmpty()&&(p.charAt(0)==s.charAt(0)||p.charAt(0)=='.'));
        //递归条件处加上对*的判断
        if (p.length()>=2&&p.charAt(1)=='*'){
            return (isMatch(s,p.substring(2))||(first_match&&isMatch(s.substring(1),p)));
        }else {
            return first_match && isMatch(s.substring(1),p.substring(1));
        }
    }
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()){return s.isEmpty();}
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[m][n] = true;//比较空串，所以为true
        for (int i = m; i >= 0; i--){
            for (int j = n; j >= 0; j--){
                if (i==m||j==n){
                    continue;
                }
                boolean first_match = (i<m&&j<n&&(s.charAt(i)==p.charAt(j)||p.charAt(j)=='.'));
                if (j+1<n&&p.charAt(j+1)=='*'){
                    dp[i][j] = dp[i][j+2]||(first_match&&dp[i+1][j]);
                }else {
                    dp[i][j] = first_match&&dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
    public int singleNonDuplicate(int[] nums) {
        if (nums.length==0){
            return -1;
        }
        int l = 0, r = nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (mid%2==1){mid--;}
            if (nums[mid]==nums[mid+1]){l = mid+2;}
            else {r = mid;}
        }
        return nums[l];
    }

   public static void main(String[] args) {
        char[] chars = {'a','b','c'};
        print1(chars,0,"");
   }
   //打印所有子串
    public static void print1(char[] chars, int index, String str){
        if (chars.length==index){
            System.out.println(str);
            return;
        }
        print1(chars,index+1,str);
        print1(chars,index+1,str+String.valueOf(chars[index]));
    }
    //打印全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (Integer num : nums){
            list.add(num);
        }
        pailie(0,lists,nums.length,list);
        return lists;
    }
    private void pailie(int first, List<List<Integer>> lists, int n, List<Integer> list){
        if (first==n){
            lists.add(new ArrayList<>(list));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(list, i, first);
            pailie(first + 1, lists, n, list);
            Collections.swap(list, i, first);
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums){
            list.add(num);
        }
        pailie2(0,lists,list,nums.length);
        return lists;
    }
    private void pailie2(int first, List<List<Integer>> lists, ArrayList<Integer> list, int n){
        if (first==n){
            lists.add(new ArrayList<>(list));
        }
        for (int i = first; i < n; i++){
            if (!isReapeat(list,first, i)) {
                Collections.swap(list, i, first);
                pailie2(first + 1, lists, list, n);
                Collections.swap(list, i, first);
            }
        }
    }
    //剪枝
    private boolean isReapeat(ArrayList<Integer> nums, int first, int n){
        int temp = nums.get(n);
        for (int i = first; i < n; i++){
            if (nums.get(i)==temp){
                return true;
            }
        }
        return false;
    }
    public static int walk(int[][] matrix, int i, int j){
        if (i==matrix.length-1&&j==matrix[0].length-1){
            return matrix[i][j];
        }
        if (i== matrix.length-1){
            return matrix[i][j] + walk(matrix,i, j+1 );
        }
        if (j==matrix[0].length-1){
            return matrix[i][j] + walk(matrix,i+1,j);
        }
        int right = walk(matrix,i+1,j);
        int down = walk(matrix,i,j+1);
        return Math.min(right,down) + matrix[i][j];
    }
    /**
    private boolean solution2(int[] matrix, int target){
        int[] sum  = new int[matrix.length];
        sum[0] = matrix[0];
        for (int i = 1; i < matrix.length; i++){
            sum[i] = matrix[i]+matrix[i-1];
        }
        boolean[][] dp = new boolean[matrix.length][matrix.length];
        if (sum[matrix.length-1]>target){
            dp[matrix.length-1][matrix.length-1] = false;
        }
        for (int i = matrix.length-1; i>=0; i--){
            for (int j = matrix.length-1;j>=0;j--){
                if (i==matrix.length-1){
                    dp[i][j] = dp[][]
                }
            }
        }
    }*/
    private boolean isSum(int[] arr, int i, int sum, int aim){
        if (i==arr.length){
            return sum==aim;
        }
        return isSum(arr,i+1,sum,aim)||isSum(arr,i+1,sum+arr[i],aim);
    }


    private int len;
    private int[] candidates;
    private List<List<Integer>> result = new ArrayList<>();



    //在进入一个新的分支之前，看一看这个数是不是和之前的数一样，如果这个数和之前的数一样，并且之前的数还未使用过，
    // 那接下来如果走这个分支，就会使用到之前那个和当前一样的数，就会发生重复，此时分支和之前的分支一模一样。
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> permute1(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (Integer num : nums){
            list.add(num);
        }
        solution(0,list,nums.length);
        return lists;
    }
    private void solution(int start,List<Integer> list,int len){
        if (start==len){
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < len; i++){
            Collections.swap(list,start,i);
            solution(start+1,list,len);
            Collections.swap(list,start,i);
        }
    }
    private boolean[] userd;
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates) {
        int len = candidates.length;
        if (len==0){return ans;}
        Arrays.sort(candidates);
        userd = new boolean[len];
        solution(candidates,new Stack<Integer>(), 0);
        return ans;
    }
    private void solution(int[] candidates, Stack<Integer> stack, int start){
        if (start==candidates.length){
            ans.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < candidates.length; i++){
            if (!userd[i]) {
                if (i > 0 &&!userd[i - 1] && candidates[i] == candidates[i - 1]){
                    continue;
                }
                stack.push(candidates[i]);
                userd[i] = true;
                solution(candidates, stack, start + 1);
                stack.pop();
                userd[i] = false;
            }
        }

    }























































}




























class ListNode {
    ListNode next;
    int val;
    public ListNode(int val) {
        this.val = val;
    }
}











































