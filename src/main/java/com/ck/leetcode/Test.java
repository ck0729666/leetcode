/**
package com.ck.leetcode;




import java.io.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception{
        HashMap<Integer, Boolean> map = new HashMap<>();
        System.out.println(map.get(1));
        //适配器模式：将原本2个接口不兼容的接口合在一起工作
        FileInputStream fileInput = new FileInputStream("dd");//FileInputStream是字节流，没有字符流读取字符的一些方法
        InputStreamReader inputStreamReader = new InputStreamReader(fileInput);//通过InputStreamReader将其转为Reader类，因此有了操作文本的文件方法
        BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);//装饰，包装为BufferedReader后有了read一行字符的功能
        //适配和装饰都是通过封装其他对象达到设计目的。
    }


    /**
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length==0 || in.length==0) {
            return null;
        }
        return reCons(pre,0,pre.length-1,in,0,in.length-1);
    }
    private TreeNode reCons(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if(startPre>endPre || startIn>endIn) {
            return null;
        }

    }



    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length()*2+1];
        int index = 0;
        for(int i = 0; i!=res.length; i++){
            res[i] = (i&1)==0 ? '#' : charArr[index++];
        }
        return res;
    }
    public static int maxLength(String str){
        if(str==null || str.length()==0){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i !=charArr.length; i++){
            pArr[i] = R > i ? Math.min(pArr[2*C-i],R-i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i]>-1){
                if(charArr[i+pArr[i]]==charArr[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(i+pArr[i]>R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max-1;
    }

    //更新结构->判断过期->加入结果集
    public static int[] getmaxWindow(int[] arr, int w) {
        if(arr==null || w<1 || arr.length<w){
            return null;
        }
        int index = 0;
        //存的是索引
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length-w+1];
        for(int i = 0; i < arr.length; i++){
            while (!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            //过期
            if(qmax.peekFirst()==i-w){
                qmax.pollFirst();
            }
            if(i >= w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    //最大值减去最小值小于或等于num的子数组数量  -> o(n)
    public static int getNum(int[] arr, int num){
        if(arr==null || arr.length==0){ return 0; }
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int start = 0;
        int end = 0;
        int res = 0;
        while (start<arr.length){
            while (end<arr.length){
                //最小值结构更新
                while(!qmin.isEmpty() && arr[qmin.peekLast()]>=arr[end]){
                    qmin.pollLast();
                }
                qmin.addLast(end);
                //最大值结构更新
                while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[end]){
                    qmax.pollLast();
                }
                qmax.addLast(end);
                if(arr[qmax.getFirst()]-arr[qmin.getFirst()]>num){
                    break;
                }
                end++;
            }
            //判断下标过期了没有
            if(qmin.peekFirst()==start){
                qmin.pollFirst();
            }
            if(qmax.peekFirst()==start){
                qmax.pollFirst();
            }
            res += end-start;
            start++;
        }
        return res;
    }
    //单调栈
    public static int maxRecFromBottom(int[] height){
        if(height==null || height.length==0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[i]<=height[stack.peek()]){
                int j = stack.pop();
                //k为左边界,j为自己，i为右边界
                int k = stack.isEmpty()? -1 : stack.peek();
                int curArea = (i-k-1)*height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            //如果不空，下面的一个就是就是j的左边界
            int k = stack.isEmpty()? -1 : stack.peek();
            int curArea = (height.length-k-1)*height[j];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }
    public static int maxRecSize(int[][] map){
        if(map==null || map.length==0 || map[0].length==0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                height[j] = map[i][j]==0 ? 0 : height[j]+1;
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(height));
        }
        return maxArea;
    }

    public static void morrisIn(TreeNode head) {
        if(head==null){
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur!=null){
            mostRight = cur.left;
            if(mostRight!=null){
                while (mostRight.right!=null && mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right==null){
                    mostRight.right = cur;
                    //在这里打印是先序System.out.println(cur.val+" ");
                    cur = cur.left;
                    continue;
                }else{
                    mostRight.right = null;
                    //后序：逆序打印左子树的右边界
                }
            }
            //如果有左子树，先处理完；如果没有，直接打印
            //在这里打印是中序System.out.println(cur.val+" ");
            cur = cur.right;
        }
        //后序：逆序打印左子树的右边界
    }
    public static void process(TreeNode head){
        if(head==null){
            return;
        }
        System.out.println(head.val);
        process(head.left);
        process(head.right);
    }











    /**
     *
     输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba
     * @param str
     * @return
     */
    /**
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if(str.length()==0){
            return list;
        }
        Helper(str.toCharArray(), 0);
        Collections.sort(list);
        return list;
    }
    private void Helper(char[] chars, int index){
        if(index == chars.length-1){
            list.add(String.valueOf(chars));
        }
        for(int j = index; j < chars.length; j++){
            if(j!=index && chars[j]==chars[index]){
                continue;
            }
            swap(chars, index, j);
            Helper(chars, index+1);
            swap(chars, index, j);
        }
    }
    private void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    //二叉搜索 -> 排序双向链表  ：  中序遍历
    private TreeNode pre = null;
    private TreeNode head = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        Res(pRootOfTree);
        return head;
    }
    private void Res(TreeNode root){
        if(root==null){
            return;
        }
        Res(root.left);
        if(head==null){
            pre = root;
            head = root;
        }else{
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        Res(root.right);
    }





    ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root==null){
            return lists;
        }
        target -= root.val;
        list.add(root.val);
        if(target==0 && root.left==null && root.right==null){
            lists.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);

        list.remove(list.size()-1);
        return lists;
    }
    //二进制中1的个数
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0){
            n = n&(n-1);
            count++;

        }
        return count;
    }

    public int minNumberInRotateArray(int [] array) {
        int length = array.length;
        if(length==0){
            return 0;
        }
        int left = 0;
        int right = length-1;
        while (left<right){
            int middle = (left+right)/2;
            if (array[middle] > array[right]){
                left = middle+1;
            }else if(array[middle] < array[right]){
                right = middle;
            }else if(array[middle] == array[right]){
                right = right-1;
            }
        }
        return array[left];
    }


    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     public int movingCount1(int threshold, int rows, int cols)
    {
        int[][] flag = new int[rows][cols];
        return Helper1(0,0,rows,cols,flag,threshold);
    }
    private int Helper1(int i, int j, int rows, int cols, int[][] flag, int threshold){
        if(i<0||i>=rows||j<0||j>=cols||flag[i][j]==1||sumInt1(i)+sumInt1(j)>threshold){
            return 0;
        }
        flag[i][j] = 1;
        return Helper(i+1,j,rows,cols,flag,threshold)+
                Helper(i,j+1,rows,cols,flag,threshold)+
                Helper(i-1,j,rows,cols,flag,threshold)+
                Helper(i,j-1,rows,cols,flag,threshold) + 1;
    }
    private int sumInt1 (int num){
        int sum = 0;
        do {
            sum += num%10;
        }while ((num = num/10)>0);
        return sum;
    }























    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e
     * 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
     * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     *
     * 链接：https://www.nowcoder.com/questionTerminal/c61c6999eecb4b8f88a98f66b273a3cc?f=discussion
     * 来源：牛客网
     *
     * 分析：回溯算法
     *  这是一个可以用回朔法解决的典型题。首先，在矩阵中任选一个格子作为路径的起点。如果路径上的第i个字符不是ch，那么这个格子不可能处在路径上的
     * 第i个位置。如果路径上的第i个字符正好是ch，那么往相邻的格子寻找路径上的第i+1个字符。除在矩阵边界上的格子之外，其他格子都有4个相邻的格子。
     * 重复这个过程直到路径上的所有字符都在矩阵中找到相应的位置。
     * 　　由于回朔法的递归特性，路径可以被开成一个栈。当在矩阵中定位了路径中前n个字符的位置之后，在与第n个字符对应的格子的周围都没有找到第n+1个
     * 字符，这个时候只要在路径上回到第n-1个字符，重新定位第n个字符。
     * 　　由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。 当矩阵中坐标为（row,col）的
     * 格子和路径字符串中相应的字符一样时，从4个相邻的格子(row,col-1),(row-1,col),(row,col+1)以及(row+1,col)中去定位路径字符串中下一个字符
     * 如果4个相邻的格子都没有匹配字符串中下一个的字符，表明当前路径字符串中字符在矩阵中的定位不正确，我们需要回到前一个，然后重新定位。
     * 　　一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < cols; j++) {
                if(helper(matrix, rows, cols, i, j, str, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i*cols+j;
        if(i<0||i>=rows||j<0||j>=cols||matrix[index]!=str[k]||flag[index]==1) {
            return false;
        }
        if(k==str.length-1) {
            return true;
        }
        flag[index] = 1;
        if(helper(matrix,rows,cols,i-1,j,str,k+1,flag)||helper(matrix,rows,cols,i+1,j,str,k+1,flag)||helper(matrix,rows,cols,i,j-1,str,k+1,flag)||helper(matrix,rows,cols,i,j+1,str,k+1,flag)) {
            return true;
        }
        flag[index]=0;
        return false;
    }







    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），
     * 因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     * @param threshold
     * @param rows
     * @param cols
     * @return

    public int movingCount(int threshold, int rows, int cols) {
        int[][] flag = new int[rows][cols];
        return Helper(0,0,rows,cols,flag,threshold);
    }
    private int Helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if(i<0||i>=rows||j<0||j>=cols||flag[i][j]==1||sumInt(i)+sumInt(j)>threshold) {
            return 0;
        }
        flag[i][j]=1;
        return Helper(i + 1, j, rows, cols, flag, threshold) +
                Helper(i - 1, j, rows, cols, flag, threshold) +
                Helper(i, j + 1, rows, cols, flag, threshold) +
                Helper(i, j - 1, rows, cols, flag, threshold) + 1;
    }
    private int sumInt(int num) {
        int sum = 0;
        do {
            sum += num%10;
        }while ((num=num/10)>0);
        return sum;
    }



    //按之字
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(pRoot==null) {
            return lists;
        }
        queue.add(pRoot);
        boolean flag = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size-- > 0) {
                TreeNode node = queue.poll();
                if(node==null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if(list.size()!=0) {
                if (!flag) {
                    Collections.reverse(list);
                }
                flag = !flag;
                lists.add(list);
            }
        }
        return lists;
    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * @param numbers
     * @return

    public String PrintMinNumber(int [] numbers) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < numbers.length; i++){
            list.add(numbers[i]);
        }
        Collections.sort(list, (o1,o2) -> {
            String s1 = o1+""+o2;
            String s2 = o2+""+o1;
            return s1.compareTo(s2);
        });
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < numbers.length; i++){
            str.append(list.get(i));
        }
        return str.toString();
    }

    /**
     *

    public static int findLastNumber(int n, int m) {
     *if (n < 1 || m < 1) return -1;
     *int[] array = new int[n];
     *int i = -1, step = 0, count = n;
     *while (count > 0) {   //跳出循环时将最后一个元素也设置为了-1
     *i++;          //指向上一个被删除对象的下一个元素。
     *if (i >= n) i = 0;  //模拟环。
     *if (array[i] == -1) continue; //跳过被删除的对象。
     *step++;                     //记录已走过的。
     *if (step == m) {               //找到待删除的对象。
     *array[i] = -1;
     *step = 0;
     *count--;
     *}
     *}
     *return i;//返回跳出循环时的i,即最后一个被设置为-1的元素
     *}

        public static int findLastNumber(int n, int m) {
            if (n < 1 || m < 1) {
                return -1;
            }
            int[] array = new int[n];
            int i = -1, step = 0, count = n;
            while (count > 0) {
                i++;
                if (i >= n) {
                    i = 0;
                }
                if (array[i] == -1) {
                    continue;
                }
                step++;
                if (step == m) {
                    array[i] = -1;
                    step = 0;
                    count--;
                }
            }
            return i;
        }
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
            ArrayList<Integer> list = new ArrayList<>();
        if(num.length==0 || size>num.length || size<=0){
            return list;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < num.length; i++){
            //更新结构
            while (!queue.isEmpty()&&num[queue.peekLast()]<num[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            //判断是否过期
            if (queue.peekFirst()==i-size){
                queue.pollFirst();
            }
            if(i>=size-1){
                list.add(num[queue.peekFirst()]);
            }
        }
        return list;
        //场景题、秒杀、算法、笔记
    }
    public Node copyRandomList1(Node pHead) {
        if(pHead==null){
            return null;
        }
        Node currNode = pHead;
        while (currNode!=null){
            Node copyNode = new Node(currNode.val);
            Node nextNode = currNode.next;
            currNode.next = copyNode;
            copyNode.next = nextNode;
            currNode = nextNode;
        }
        currNode = pHead;
        while (currNode!=null){
            currNode.next.random = currNode.random==null ? null : currNode.random.next;
            currNode = currNode.next.next;
        }
        currNode = pHead;
        Node copyHead = pHead.next;
        while (currNode!=null) {
            Node copyNode = currNode.next;
            currNode.next = copyNode.next;
            copyNode.next = currNode.next==null?null:copyNode.next.next;
            currNode = currNode.next;
            //copyNode.next = copyNode.next==null ? null : copyNode.next.next;
            //copyNode = copyNode.next;
            //currNode = copyNode.next;

        }
        return copyHead;
        /**
         * Node cloneNode = currentNode.next;
         * currentNode.next = cloneNode.next;
         * cloneNode.next = cloneNode.next==null?null:cloneNode.next.next;
         * currentNode = currentNode.next;
         */





































/**

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

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }
}*/