package com.ck.demo.test;

import com.sun.deploy.panel.TreeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class Test2 {

    public static String replaceSpace(StringBuffer str) {
        int length = str.length();
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if(String.valueOf(i).equals(" ")) {
                result.append("%20");
            } else {
                result.append(b);
            }
        }
        return result.toString();
    }
    /**
    public static void main(String[] args) {
        String[] strs = {"castomer", "caar", "cat"};
        Arrays.sort(strs);
        for(String str : strs) {
            System.out.println(str);
        }
        System.out.println(Test2.replaceSpace(strs));
    }*/

    public static String replaceSpace(String[] strs) {
        if(!check(strs)){
            return "";
        }
        Arrays.sort(strs);
        int m = strs[0].length();
        int n = strs[strs.length-1].length();
        int length = Math.min(m, n);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            if(strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                stringBuilder.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
    public static boolean check(String[] strs) {
        if(strs.length != 0) {
            for(int i = 0; i < strs.length; i++) {
                if(strs[i].length() == 0 || strs[i] == null) {
                    return false;
                }
            }
        }
        return true;
    }
    public int sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += sum_Solution(n-1)) > 0);
        return sum;
    }
    public int add(int a, int b) {
        return b == 0 ? a : add(a^b, (a&b)<<1);
    }
    /**
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= A[i], i++) {
            B[i] = product;
        }
        for (int i = n, product = 1; i >= 0; product *= A[i], i--) {
            B[i] *= product;
        }
        return {1,2};
    }*/
    public int strToInt(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for(int i = 0; i < str.length(); i++) {
            char c= str.charAt(i);
            if(i == 0 && (c == '+' || c == '-')) {
                continue;
            }
            if(c < '0' || c > '9') {
                return 0;
            }
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }
    //二叉查找树
    public TreeNode lowestAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestAncestor(root.left, p, q);
        }
        if(root.val < p.val && root.val < q.val) {
            return lowestAncestor(root.right, p ,q);
        }
        return root;
    }
    //普通二叉树
    public TreeNode lowestAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestAncestor1(root.left, p, q);
        TreeNode right = lowestAncestor1(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
    //股票最大利润
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int soFarMin = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
        }
        return maxProfit;
    }
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }
    public boolean isCotinous(int[] nums) {
        if(nums.length < 5) {
            return false;
        }
        Arrays.sort(nums);
        //统计癞子数量
        int count = 0;
        for(int num : nums) {
            if(num == 0) {
                count++;
            }

        }
        //使用癞子去补全不连续的顺子
        for(int i = count; i < nums.length - 1; i++) {
            if (nums[i+1] == nums[i]) {
                return false;
            }
            count -= nums[i+1] - nums[i] - 1;
        }
        return count >= 0;
    }

    public int firstFind(String array) {
        BitSet bitSet1 = new BitSet(256);
        BitSet bitSet2 = new BitSet(256);
        for(char c : array.toCharArray()) {
            if(!bitSet1.get(c) && !bitSet2.get(c)) {
                bitSet1.set(c);
            }else if(bitSet1.get(c) && !bitSet2.get(c)) {
                bitSet2.set(c);
            }
        }
        for(int i = 0; i < array.length(); i++) {
            char c = array.charAt(i);
            if(bitSet1.get(c) && !bitSet2.get(c)){
                return i;
            }
        }
        return -1;
    }
    //找两个链表的第一个公共节点
    //遍历链表A，然后让他重新开始遍历链表B
    //同样，遍历B，再遍历A
    //这样就能控制访问A和B两个链表的指针能同时访问到交点
    public ListNode fingFirst(ListNode head1, ListNode head2) {
        ListNode l1 = head1, l2 = head2;
        while(l1 != l2) {
            l1 = (l1 == null) ? head2 : l1.next;
            l2 = (l2 == null) ? head1 : l2.next;
        }
        return l1;
    }
    public int getNumbers(int[] nums, int k) {
        int first = binarySearch(nums, k);
        int last = binarySearch(nums, k+1);
        return (first == nums.length || nums[first] != k) ? 0 : last - first;
    }
    private int binarySearch(int[] nums, int k) {
        int l = 0, h = nums.length;
        while(l < h) {
            int m = l + (h - l) / 2;
            if(nums[m] >= k) {
                 h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }//2,2,3,3,3

    public static void main(String[] args) {
        int[] numbers = {2,2,3,3,3,4,5};
        Test2 test = new Test2();
        System.out.println(test.binarySearch(numbers,2));
        System.out.println(test.binarySearch(numbers,3));
        System.out.println(test.getNumbers(numbers, 3));
        System.out.println("------------");
        System.out.println(Test2.search(numbers, 0, 7, 4));

    }

    //二分查找
    public static  int search(int[] array, int start, int end, int findVaule) {
        if(array == null) {
            return -1;
        }

        while(start <= end) {
            int middle = (start+end) / 2;
            int middleValue = array[middle];
            if(findVaule == middleValue) {
                return middle;
            }else if(findVaule < middleValue) {
                end = middle - 1;
            }else {
                start = middle + 1;
            }
        }
        return -1;
    }
    public int erfenSearch(int[] nums, int start, int end, int value) {
        if(nums.length == 0) {
            return -1;
        }
        while (start <= end) {
            int middle = (end-start)/2+start;
            int middleValue = nums[middle];
            if(middleValue>value) {
                end = middle-1;
            }else if(middleValue<value) {
                start = middle+1;
            }else {
                return middle;
            }
        }
        return -1;
    }
    private TreeNode ret;
    private int count = 0;
    public TreeNode kNode(TreeNode pRoot, int k) {
        inOrder(pRoot, k);
        return ret;
    }
    private void inOrder(TreeNode root, int k) {
        if (root == null || count >= k) {
            return;
        }
        inOrder(root.left, k);
        count++;
        if (count == k) {
            ret = root;
        }
        inOrder(root.right, k);
    }
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }
    public int depth(TreeNode root) {
        return root==null?0:1+Math.max(depth(root.left), depth(root.right));
    }
    private boolean isBalanced = true;
    public boolean isBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }
    private int height(TreeNode root) {
        if(root == null || !isBalanced) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if(Math.abs(left - right) > 1){
            isBalanced = false;
        }
        return 1+Math.max(left, right);
    }
    public void find2(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        //得到的diff为两个不同的数^的结果
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff;
        for (int num : nums) {
            if((num&diff) == 0) {
                num1[0] ^= num;
            }
            else {
                num2[0] ^= num;
            }
        }

    }
        public String ReverseSentence(String str) {
            int n = str.length();
            char[] c = str.toCharArray();
            int i = 0, j = 0;
            while (j <= n) {
                if(j == n || c[j] == ' ') {
                    reverse(c, i, j-1);
                    i = j + 1;
                }
                j++;
            }
            reverse(c, 0, n-1);
            return new String(c);
        }
        private void swap(char[] c, int a, int b) {
            char t;
            t = c[a];
            c[a] = c[b];
            c[b] = t;
        }
        private void reverse(char[] c, int a, int b) {
            while(a < b) {
                swap(c, a, b);
            }
        }
        public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            int plow = 1, phigh = 2;
            while(plow<phigh) {
                int cur = (phigh+plow)/2;
                if(cur == sum) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for(int i = plow; i <= phigh; i++) {
                        list.add(i);
                    }
                    result.add(list);
                    plow++;
                }else if(cur < sum) {
                    phigh++;
                }else {
                    plow++;
                }
            }
            return result;
        }
        public ArrayList<Integer> findNumbers(int[] array, int sum) {
            int i = 0, j = array.length - 1;
            ArrayList<Integer> list = new ArrayList<Integer>();
            if(array == null || array.length < 2) {
                return list;
            }
            while(i < j) {
                if(sum == array[i]+array[j]) {
                    list.add(array[i]);
                    list.add(array[j]);
                    return list;
                }else if(sum < array[i]+array[j]) {
                    j--;
                }else {
                    i++;
                }
            }
            return list;
        }














//      5
//  3       7
//2   4   6   8
//

}
class TreeNode {
    int val;
    public TreeNode(int val) {
        this.val = val;
    }
    TreeNode left = null;
    TreeNode right = null;
}
