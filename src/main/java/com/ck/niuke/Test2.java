package com.ck.niuke;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test2 {
    //int count = 0;
    public static void main(String[] args) {
        int[] array = {1,2,367,87,7};
        sort(array, 0, 4);
        for(int i = 0; i < 4; i++) {
            System.out.print(array[i]);
        }
    }
    public static void sort(int a[], int low, int high) {
        int i = low;
        int j = high;
        int key = a[low];
        if(low<high) {
            while (i<j){
                while(i<j&&key<=a[j]){
                    j--;
                }
                a[i]=a[j];
                while (i<j&&key>=a[i]){
                    i++;
                }
                a[j]=a[i];
                a[i]=key;
            }
            sort(a,low,i-1);
            sort(a,i+1,high);
        }
    }





















    public int find22(int[] array){
        int max = array[0];
        int res = array[0];
        for(int i = 1; i < array.length; i++) {
            max = Math.max(max+array[i], array[i]);
            res = Math.max(res, max);
        }
        return res;
    }


    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot==null){
            return false;
        }
        return isSymm(pRoot.left, pRoot.right);
    }
    boolean isSymm(TreeNode node1, TreeNode node2) {
        if(node1==null && node2==null) {
            return true;
        }
        if(node1==null || node2==null) {
            return false;
        }
        if(node1.val!=node2.val) {
            return false;
        }
        return isSymm(node1.left, node2.right) && isSymm(node1.right, node2.left);
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{
                        map.get(target-nums[i]), i
                };
            }else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("no!");
    }
    public boolean isBalanced(TreeNode root) {
        return getDepth1(root)!= -1;
    }
    private int getDepth1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = getDepth1(root.left);
        if(left == -1) {
            return -1;
        }
        int right = getDepth1(root.right);
        if(right == -1) {
            return -1;
        }
        return Math.abs(left-right) > 1 ? -1 : Math.max(left, right)+1;
    }
    public int myAtoi1(String str) {
        String s = str.trim();
        int flag = 0;
        if(str.length()==0){
            return 0;
        }
        if(s.charAt(0) == '+'){
            flag = 1;
        }
        if(s.charAt(0) == '-'){
            flag = 2;
        }
        int res = 0;
        int i = 0;
        for(i = (flag>0)?1:0; i < s.length(); i++) {
            if(str.charAt(i) > '0' || str.charAt(i) < '9'){
                break;
            }
            res = res * 10 + (s.charAt(i)-'0');
        }
        return flag == 2 ? -res : res;
    }
    public int myAtoi(String str) {
        String strr = str.trim();
        String strrr = null;
        //字符串不为空时且字符串不全是空白字符串时才转换
        if(strr!=null &&strr.isEmpty()==false) {
            char f = strr.charAt(0);
            //判断第一个字符
            if (f > '0' && f <= '9' || f == '+' || f == '-') {
                strrr = strr.substring(0, 1);

                for (int i = 1; i < strr.length(); i++) {
                    if (strr.charAt(i) >= '0' && strr.charAt(i) <= '9') {
                        strrr = strr.substring(0, i + 1);
                    } else {
                        break;
                    }
                }
            }
        }
            if(strrr == null || strrr.equals("+")||strrr.equals("-")) {
                return 0;
            }
            int num = 0;
            try{
                num = Integer.parseInt(strrr);
            } catch(Exception e) {
                if(strrr.charAt(0)=='-'){
                    return Integer.MIN_VALUE;
                }
                return Integer.MAX_VALUE;
            }
            return num;
        }


    /**
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for(int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j-i+1);

        }
    }*/
    //7.
    public int reverse(int x) {
        int rev = 0;
        while (x!=0) {
            int pop = x % 10;
            x /= 10;
            if(rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {return 0;}
            if(rev < Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop < -8)) {return 0;}
            rev = rev * 10 + pop;
        }
        return rev;
    }



























    public int GetNumberOfK(int [] array , int k) {
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int firstK = firstK(array, k, 0, length-1);
        int lastK = lastK(array, k, 0, length-1);
        if(lastK!=-1 && firstK!=-1) {
            return lastK - firstK + 1;
        }
        return 0;
    }
    public int firstK(int[] array, int k, int start, int end) {
        if(array.length == 0) {
            return -1;
        }
        if(end<start) {
            return -1;
        }
        int mid = (end+start)>>1;
        if(array[mid] > k) {
            return firstK(array, k, start, mid-1);
        }else if(array[mid] < k) {
            return firstK(array, k, mid+1, end);
        }else if(array[mid-1]==k && mid-1>=0) {
            return firstK(array, k, start, mid-1);
        }else {
            return mid;
        }
    }
    public int lastK(int[] array, int k, int start, int end) {
        int len = array.length;
        if(len==0) {
            return 0;
        }
        int mid = (end+start)>>1;
        while (start <= end) {
            if(array[mid] > k) {
                end = mid -1;
            }else if(array[mid] < k){
                start = mid + 1;
            }else if(array[mid+1]==k && mid+1<len){
                start = mid + 1;
            }else {
                return mid;
            }
            mid = (end+start)>>1;
        }
        return -1;
    }
    /**
     * public int GetNumberOfK(int [] array , int k) {
     *         int length = array.length;
     *         if(length == 0){
     *             return 0;
     *         }
     *         int firstK = getFirstK(array, k, 0, length-1);
     *         int lastK = getLastK(array, k, 0, length-1);
     *         if(firstK != -1 && lastK != -1){
     *              return lastK - firstK + 1;
     *         }
     *         return 0;
     *     }
     *     //递归写法
     *     private int getFirstK(int [] array , int k, int start, int end){
     *         if(start > end){
     *             return -1;
     *         }
     *         int mid = (start + end) >> 1;
     *         if(array[mid] > k){
     *             return getFirstK(array, k, start, mid-1);
     *         }else if (array[mid] < k){
     *             return getFirstK(array, k, mid+1, end);
     *         }else if(mid-1 >=0 && array[mid-1] == k){
     *             return getFirstK(array, k, start, mid-1);
     *         }else{
     *             return mid;
     *         }
     *     }
     *     //循环写法
     *     private int getLastK(int [] array , int k, int start, int end){
     *         int length = array.length;
     *         int mid = (start + end) >> 1;
     *         while(start <= end){
     *             if(array[mid] > k){
     *                 end = mid-1;
     *             }else if(array[mid] < k){
     *                 start = mid+1;
     *             }else if(mid+1 < length && array[mid+1] == k){
     *                 start = mid+1;
     *             }else{
     *                 return mid;
     *             }
     *             mid = (start + end) >> 1;
     *         }
     *         return -1;
     *     }
     */

    public boolean IsBalanced_Solution(TreeNode root) {
        return getDepth(root) != -1;
    }

    /**
     *
     * 这种做法有很明显的问题，在判断上层结点的时候，
     * 会多次重复遍历下层结点，增加了不必要的开销。
     * 如果改为从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；
     * 如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次。
     * @param root
     * @return
     */
    private int getDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if(left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if(right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1+Math.max(left, right);
    }
    /**
    public String LeftRotateString(String str,int n) {
        StringBuilder sb = new StringBuilder();
        if(str.length() == 0 || n <= 0 || n > str.length()){
            return str;
        }
        sb.append(str.substring(n, str.length()));
        sb.append(str.substring(0, n));
        return sb.toString();
    }*/
    public void reverse(char[] chars, int low, int high) {
        char temp;
        while (low < high) {
            temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high--;
        }
    }
    //三次翻转
    public String LeftRotateString(String str,int n) {
        char[] chars = str.toCharArray();
        if(chars.length < n) {
            return "";
        }
        reverse(chars, 0, n-1);
        reverse(chars, n, chars.length-1);
        reverse(chars, 0, chars.length-1);
        StringBuilder sb = new StringBuilder(chars.length);
        for(char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
    public int Sum_Solution(int n) {
        boolean flag = (n>0) && ((n += Sum_Solution(n-1))>0);
        return n;
    }

    public int Add(int num1,int num2) {
        while (num2!=0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;//计算进位
            num1 = temp;
        }
        return num1;
    }
    public int StrToInt(String str) {
        if(str.length() == 0) {
            return 0;
        }
        int flag = 0;
        if(str.charAt(0) == '-') {
            flag = 1;
        }
        if(str.charAt(0) == '+') {
            flag = 2;
        }
        long res = 0L;
        char[] chars = str.toCharArray();
        int i = 0;
            for(i = flag>0?1:0; i < chars.length; i++) {
                if(chars[i] > '9' || chars[i] < '0') {
                    return 0;
                }
                res = res *10 +(chars[i]-'0');
            }

        return flag == 1 ? (int) res : -(int)res;
    }
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        boolean[] k = new boolean[length];
        for(int i = 0; i < length; i++) {
            if(k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }else {
                k[numbers[i]] = true;
            }
        }
        return false;
    }





}

class MyThread implements Runnable {
    private int ticket = 10;
    @Override
    public void run() {
        while (true) {

                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + ticket);
                } else {
                    break;
                }

        }
    }
}
class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    public TreeNode(int val) {
        this.val = val;
    }
}




