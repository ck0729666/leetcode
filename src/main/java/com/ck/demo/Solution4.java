package com.ck.demo;

import sun.nio.cs.ext.MacThai;

import java.util.*;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;


public class Solution4 {

    public static void main(String[] args) {

        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.Sum_Solution(5));

    }
    //二叉树后序遍历
        public boolean VerifySquenceOfBST(int [] sequence) {
            if(sequence!=null || sequence.length==0) {
                return false;
            }
            return verify(sequence, 0, sequence.length-1);
        }
        public boolean verify(int[] sequence, int first, int last){
            if(last - first <= 1) {
                return true;
            }
            int curr = first;
            int rootNode = sequence[last];
            while(curr < last && sequence[curr] <= rootNode) {
                curr++;
            }
            for(int i = curr; i < last; i++) {
                if(sequence[i] < rootNode) {
                    return false;
                }
            }
            return verify(sequence, first, curr - 1) && verify(sequence, curr, last - 1);
        }

    public int FindGreatestSumOfSubArray(int[] array) {
        if(array.length == 1) {
            return array[0];
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < array.length; i++) {
            int sum = 0;
            for(int j = i; j < array.length; j++) {
                sum += array[j];
                list.add(sum);
            }
        }
        Collections.sort(list);
        return list.get(list.size()-1);

    }
    public String PrintMinNumber(int [] numbers) {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        /**
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1+""+o2;
                String s2 = o2+""+o1;
                return s1.compareTo(s2);
            }
        });*/
        Collections.sort(list, (o1, o2) ->{
            String s1 = o1+""+o2;
            String s2 = o2+""+o1;
            return s1.compareTo(s2);
        });
        for(Integer l : list) {
            sb.append(l);
        }
        return sb.toString();
    }

    public int TreeDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);

        return nLeft>nRight ? (nLeft+1) : (nRight+1);
    }



    //统计一个数字在排序数组中出现的次数。
    public class Solution {
        public int GetNumberOfK(int [] array , int k) {
            int length = array.length;
            if(length == 0) {
                return 0;
            }
            int firstK = getFirstK(array, 0, length-1, k);
            int lastK = getLastK(array, 0, length-1, k);
            if(lastK!= -1 && firstK!=-1) {
                return lastK - firstK + 1;
            }
            return 0;
        }
        public int getFirstK(int[] array, int start, int end, int k) {
            if(end < start) {
                return -1;
            }
            int mid = (start + end) >> 1;
            if(array[mid] < k) {
                return getFirstK(array, mid+1, end, k);
            }else if(array[mid] > k) {
                return getFirstK(array, start, mid-1, k);
            }else if(array[mid-1]==k && mid-1>=0) {
                return getFirstK(array, start, mid-1, k);
            }else {
                return mid;
            }
        }
        public int getLastK(int[] array, int start, int end, int k) {
            int mid = (start+end) >> 1;
            while(start <= end) {
                if(array[mid] < k) {
                    start = mid + 1;
                }else if(array[mid] > k) {
                    end = mid - 1;
                }else if(array[mid+1]==k && mid+1<array.length) {
                    start = mid + 1;
                }else {
                    return mid;
                }
                mid = (start+end)>>1;
            }
            return -1;
        }
    }
    public boolean isBalanced_Solution(TreeNode root) {
        return getDepth(root) != -1;
    }
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
        return Math.abs(left - right)>1 ? -1 : 1+ Math.max(left, right);
    }
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array == null || array.length<2) {
            return;
        }
        int temp = 0;
        for(int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }
        int indexOf1 = findFirstBit(temp);
        for(int i = 0; i < array.length; i++) {
            if(isBit(array[i], indexOf1)) {
                num1[0]^=array[i];
            }else {
                num2[0]^=array[i];
            }
        }
    }
    public int findFirstBit(int num) {
        int indexBit = 0;
        while(((num&1)==0)&&(indexBit)<8*4){
            num = num>>1;
            ++indexBit;
        }
        return indexBit;
    }
    public boolean isBit(int num, int indexBit) {
        num = num >> indexBit;
        return (num&1) == 1;
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int begin = 1, end = 2;
        while(begin<end) {
            int currSum = (begin+end) * (end-begin+1) / 2;
            if(currSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for(int i = begin; i < end; i++) {
                    list.add(i);
                }
                result.add(list);
            }else if(currSum < sum) {
                end++;
            }else{
                begin++;
            }
        }



        return result;
    }
    public String LeftRotateString(String str,int n) {
        char[] chars = str.toCharArray();
        if(chars.length < n) {
            return  "";
        }
        reverse(chars, 0, n-1);
        reverse(chars, n, chars.length-1);
        reverse(chars, 0 , chars.length-1);
        StringBuilder sb = new StringBuilder();
        /**
        for(char c : chars) {
            sb.append(c);
        }
        return sb.toString();*/
        return chars.toString();
    }
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
    public int Sum_Solution(int n) {
        //int sum = n;
        boolean flag = (n>0) && ((n += Sum_Solution(n-1))>0);
        return n;
    }
    public int Add(int num1,int num2) {

        //例如：5+7=12
        /**
         * 相加各位的值，不算进位，得到2
         * 计算进位，得到10
         * 重复以上两步，相加的值编程2和10，得到12
         *
         * 5-101，7-111
         * 相加各位的值，得到010，二进制每位相加相当于各位做异或操作，101^111
         * 计算进位制，得到1010，相当于各位做与操作得到101，再左移一位得到1010
         * 重复以上两步，各位相加，010^1010=1000,进位值为100=(010&1010)<<1，继续重复，010^1000=1100,进位值为0，跳出循环，1100为最终结果
         *
         */
        while(num2!=0) {
            int temp = num1^num2;
            num2 = (num1&num2)<<1;
            num1 = temp;

        }
        return num1;
    }
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        boolean[] k = new boolean[length];
        for(int i= 0; i < length; i++) {
            if(k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }else {
                k[numbers[i]] = true;
            }
        }
        return false;
    }



















    public class Solution1 {
        public int GetNumberOfK(int [] array , int k) {
            int length = array.length;
            if(length == 0){
                return 0;
            }
            int firstK = getFirstK(array, k, 0, length-1);
            int lastK = getLastK(array, k, 0, length-1);
            if(firstK != -1 && lastK != -1){
                return lastK - firstK + 1;
            }
            return 0;
        }
        //递归写法
        private int getFirstK(int [] array , int k, int start, int end){
            if(start > end){
                return -1;
            }
            int mid = (start + end) >> 1;
            if(array[mid] > k){
                return getFirstK(array, k, start, mid-1);
            }else if (array[mid] < k){
                return getFirstK(array, k, mid+1, end);
            }else if(mid-1 >=0 && array[mid-1] == k){
                return getFirstK(array, k, start, mid-1);
            }else{
                return mid;
            }
        }
        //循环写法
        private int getLastK(int [] array , int k, int start, int end){
            int length = array.length;
            int mid = (start + end) >> 1;
            while(start <= end){
                if(array[mid] > k){
                    end = mid-1;
                }else if(array[mid] < k){
                    start = mid+1;
                }else if(mid+1 < length && array[mid+1] == k){
                    start = mid+1;
                }else{
                    return mid;
                }
                mid = (start + end) >> 1;
            }
            return -1;
        }
    }

    /**
     * 1.若该节点存在右，则下一为右的最左
     * 1.若为父节点的左，则下一为父
     * 2.若为父节点的右，则下一为父往上，直到找到一个节点为其父的左，则其父为下一
     * @param node
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(pNode==null) {
            return pNode;
        }
        if(pNode.right!=null) {
            pNode = pNode.right;
            while (pNode.left!=null) {
                pNode = pNode.left;
            }
            return pNode;
        }else if(pNode.next!=null && pNode.next.left==pNode) {
            return pNode.next;
        }else if(pNode.next!=null && pNode.next.right==pNode) {
            pNode = pNode.next;
            while (pNode.next!=null && pNode.next.left!=pNode) {
                pNode = pNode.next;
            }
            return pNode.next;
        }else {
            return pNode.next;
        }

    }
    public HashMap<Character, Integer> map = new HashMap<>();
    public ArrayList<Character> list = new ArrayList<>();
    public void Insert(char ch)
    {
        list.add(ch);
        if(map.containsKey(ch)) {
            int value = map.get(ch);
            map.put(ch, value+1);
        } else {
            map.put(ch, 1);
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for(Character c : list) {
            if(map.get(c) == 1) {
                return c;
            }
        }
        return '#';
    }
    public boolean isNumeric(char[] str) {
        int len = str.length;
        boolean sign = false, decimal = false, hasE = false;
        for(int i = 0; i < str.length; i++) {
            if(str[i]=='e' || str[i] == 'E') {
                if(i == len-1) {
                    return false;
                }
                if(hasE) {
                    return false;
                }
                hasE = true;
            } else if(str[i] == '+' || str[i] == '-') {
                if(sign && str[i-1] != 'e' && str[i-1] != 'E') {
                    return false;
                }
                if(!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E') {
                    return false;
                }
                sign = true;
            } else if(str[i] == '.') {
                //e后面不能出现. 且不能出现两次.
                if(hasE || decimal) {
                    return false;
                }
                decimal = true;
            }
            else if(str[i] > '9' || str[i] < '0') {
                return false;
            }
        }
        return true;
    }
    private int count = 0;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, (o1, o2) ->{return o2-o1;});
    public void Insert(Integer num) {
        if(count%2 == 0) {
            maxHeap.offer(num);
            int filteredNum = maxHeap.poll();
            minHeap.offer(filteredNum);
        }else {
            minHeap.offer(num);
            int fiteredNum = minHeap.poll();
            maxHeap.offer(fiteredNum);
        }
        count++;
    }

    public Double GetMedian() {
        if(count%2 == 0) {
            return new Double((maxHeap.peek()+minHeap.peek())/2);
        }else {
            return new Double(minHeap.peek());
        }
    }
























        /**
if (pHead==null || pHead.next==null){return pHead;}
    ListNode Head = new ListNode(0);
    Head.next = pHead;
    ListNode pre  = Head;
    ListNode last = Head.next;
while (last!=null){
        if(last.next!=null && last.val == last.next.val){
            // 找到最后的一个相同节点
            while (last.next!=null && last.val == last.next.val){
                last = last.next;
            }
            pre.next = last.next;
            last = last.next;
        }else{
            pre = pre.next;
            last = last.next;
        }
    }
return Head.next;*/







}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;

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




































