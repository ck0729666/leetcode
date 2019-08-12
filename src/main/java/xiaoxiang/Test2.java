/*
*
package xiaoxiang;


import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;
import org.omg.PortableInterceptor.INACTIVE;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

import javax.lang.model.type.ArrayType;
import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        System.out.println(reverseNum(num));

        int num2 = scanner.nextInt();
        System.out.println(soluTion(num2));
         String num3 = scanner.next();
         System.out.println(averange1(num3));
        int[] arr = {12,85,25,16,34,23,49,95,17,61};
        merSort(arr, 0, 9);
        System.out.println("归并排序结果：");
        for(int i = 0; i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
        Sort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }
    private static int reverseNum(int number){
        String str = String.valueOf(number);
        char[] chars = str.toCharArray();
        int index = chars.length-1;
        int jinwei = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < chars.length; i++){
            int sum = Integer.valueOf(chars[i]-48) + Integer.valueOf(chars[index--]-48) + jinwei;
            jinwei = sum/10;
            sb.append(sum%10);
        }
        if(jinwei > 0){
            sb.append(jinwei);
        }
        return Integer.valueOf(sb.reverse().toString());
    }
    private static String soluTion(int n){
        StringBuilder sb = new StringBuilder();
        while (n!=0){
            if(n%2==0){
                n = (n-2)/2;
                sb.append("2");
            }else{
                n = (n-1)/2;
                sb.append("1");
            }
        }
        return sb.reverse().toString();
    }
     private static double averange(String str){
         HashMap<Character, Integer> map = new HashMap<>();
         //ArrayList<Character> list = new ArrayList<>();
         int count = 0;
         for(int i = 0; i < str.length(); i++){
             if(map.containsKey(str.charAt(i))){
                 map.put(str.charAt(i), map.get(str.charAt(i)+1));
             }else {
                 map.put(str.charAt(i), 1);
                 count++;
             }
         }
         return Double.valueOf(str.length())/Double.valueOf(count);
     }
    private static double averange1(String str){
        int count = 1;
        for(int i = 0; i < str.length()-1; i++){
            if(str.charAt(i)!=str.charAt(i+1)){
                count++;
            }
        }
        return str.length()/Double.valueOf(count);
    }
    //无重复的最长子串 aaabdbsss -> abd
    */
/**
     * 通过使用 HashSet 作为滑动窗口，我们可以用 O(1)O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
     *
     * 滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     * 即 [i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。
     *
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。
     * 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。
     * 此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。如果我们对所有的 ii 这样做，就可以得到答案。
     * @param s
     * @return

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int solution = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i<length || j<length){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                solution = Math.max(solution, j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return solution;
    }
    //最长回文串
    /**
    public String longestPalindrome(String s) {

    }
    public int[] twoSum(int[] nums, int target) {
        if (nums.length==0){
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }else{
                map.put(nums[i], i);
            }
        }
        return null;
    }
    //最长的山脉 845 852 941
    public int longestMountain(int[] A) {
        if(A==null||A.length<3){
            return 0;
        }
        int res = 0;
        for(int i = 1; i < A.length-1; i++){
            if(A[i-1]<A[i] && A[i]>A[i+1]){
                int l = i-1;
                int r = i+1;
                while (l>0 && A[l]>A[l-1]){
                    l--;
                }
                while (r<A.length-1 && A[r]>A[r+1]){
                    r++;
                }
                res = Math.max(r-l+1, res);
            }
        }
        return res;
    }
    public int peakIndexInMountainArray(int[] A) {
        int l = 1, r = A.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public int peakIndexInMountainArray1(int[] A) {
        if(A.length==0 || A.length<3){
            return 0;
        }
        int l = 1,r = A.length-1;
        while (l<r){
            int mid = l + (r-l)/2;
            if (A[mid]>A[mid-1] && A[mid]>A[mid+1]){
                return mid;
            }else if(A[mid]>A[mid-1] && A[mid]<A[mid+1]){
                l = mid;
            }else{
                r = mid;
            }
        }
        return 0;
    }

    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int n = A.length - 1;
        int l = 0, r = n;
        while (l < n) {
            if (A[l] < A[l + 1]) {
                l++;
            } else {
                break;
            }
        }
        while (r >= 1) {
            if (A[r] < A[r - 1]) {
                r--;
            } else {
                break;
            }
        }
        return l > 0 && r < n && l == r;
    }


    public boolean validMountainArray1(int[] A) {
        if(A.length<3){
            return false;
        }
        int flag = 0;
        for(int i = 1; i < A.length-1; i++){
            if (A[i-1]<A[i]){
                flag++;
            }
        }
        for(int j = flag; j < A.length-1; j++){
            if(A[j]<A[j+1]){
                return false;
            }
        }
        return true;
    }
    //三指 快排
    //通用swap
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int partition(int[] a, int l, int r){
        int i,q = l;
        for(i = l+1;i<=r;i++){
            if (a[l]>a[i]){
                swap(a,i,++q);
            }
        }
        swap(a,l,q);
        return q;
    }

    public static void Sort(int[] a, int l , int r){
        if(l>=r){
            return;
        }
        int q = partition(a, l, r);
        Sort(a,l,q-1);
        Sort(a,q+1,r);
    }
    //二叉树的最小深度
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if(root.left!=null){
            min_depth = Math.min(min_depth,minDepth(root.left));
        }
        if(root.right!=null){
            min_depth = Math.min(min_depth,minDepth(root.right));
        }
        return min_depth+1;
    }
    public int reverse(int x) {
        int rev = 0;
        while (x!=0){
            int pop = x%10;
            x /= 10;
            if (rev>Integer.MAX_VALUE/10 || (rev==Integer.MAX_VALUE/10 && pop>7)){return 0;}
            if(rev<Integer.MIN_VALUE/10 || (rev==Integer.MIN_VALUE/10 && pop<-8)){return 0;}
            rev  = rev*10 + pop;
        }
        return rev;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1!=null && l2!=null){
            if(l1.val<=l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1==null?l2:l1;
        while (l1!=null){
            pre.next=l1;
            l1=l1.next;
            pre=pre.next;
        }
        return prehead.next;
    }
    //找出数组中三数相加=0的所有集合
    //思路：先排序
    //若nums[i]>0,break
    //若i>0&&nums[i]==nums[i-1],continue
    //sum=0,去重L
    //sum=0,去重R
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        int length = nums.length;
        if(length==0||length<3){
            return lists;
        }
        Arrays.sort(nums);
        for(int i = 0; i < length; i++){
            if (nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int L = i+1;
            int R = length-1;
            while (L<R){
                int sum = nums[i]+nums[L]+nums[R];
                if (sum==0){
                    lists.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R&&nums[L]==nums[L+1]){L++;}
                    while (L<R&&nums[R]==nums[R-1]){R--;}
                    L++;
                    R--;
                }else if(sum>0){
                    R--;
                }else {
                    L++;
                }
            }
        }
        return lists;
    }
    //归并排序：拆分直到子集合元素个数为1，比较相邻子集合合并成为有序集合，合并直到集合次数为1
    public static void merge(int[] arr, int left, int mid, int right){
        int[] newArr = new int[right-left+1];
        int i = left, j = mid+1;
        int k = 0;
        while (i<=mid&&j<=right){
            if(arr[i]<arr[j]){
                newArr[k++] = arr[i++];
            }else {
                newArr[k++] = arr[j++];
            }
        }
        while (i<=mid){
            newArr[k++] = arr[i++];
        }
        while (j<=right){
            newArr[k++] = arr[j++];
        }
        for(int a = 0; a < newArr.length; a++){
            arr[a+left] = newArr[a];
        }
    }
    public static void merSort(int[] arr, int left, int right){
        if(left<right){
            int mid = left+(right-left)/2;
            merSort(arr,left,mid);
            merSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    //合并多个链表  ->归并思想，自底向上，依次合并
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        return solve(lists, 0, lists.length-1);
    }
    private ListNode solve(ListNode[] lists, int left, int right){
        if(left==right){
            return lists[left];
        }
        int mid = (left+right)>>1;
        ListNode lNode = solve(lists,left,mid);
        ListNode rNode = solve(lists,mid+1,right);
        return merge(lNode,rNode);
    }
    private ListNode merge(ListNode node1, ListNode node2){
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        while (node1!=null&&node2!=null) {
                if (node1.val <= node2.val) {
                    pre.next = node1;
                    node1 = node1.next;
                } else {
                    pre.next = node2;
                    node2 = node2.next;
                }
                pre = pre.next;
        }
        while (node1!=null){
            pre.next = node1;
            node1 = node1.next;
        }
        while (node2!=null){
            pre.next = node2;
            node2 = node2.next;
        }
        return preHead.next;
    }




































/**
 * int n = s.length();
 *         Set<Character> set = new HashSet<>();
 *         int ans = 0, i = 0, j = 0;
 *         while (i < n && j < n) {
 *             // try to extend the range [i, j]
 *             if (!set.contains(s.charAt(j))){
 *                 set.add(s.charAt(j++));
 *                 ans = Math.max(ans, j - i);
 *             }
 *             else {
 *                 set.remove(s.charAt(i++));
 *             }
 *         }
 *         return ans;
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/two-sum/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
 * 来源：力扣（LeetCode）
 * 著作权作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

















*/
