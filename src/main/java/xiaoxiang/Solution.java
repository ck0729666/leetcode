package xiaoxiang;


import sun.nio.cs.ext.MacHebrew;

import java.security.SecureRandom;
import java.util.*;

public class Solution {
    //完全二叉树
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        return solution(root,1,mostLevelLeft(root,1));
    }
    private int solution(TreeNode root,int level,int height){
        if(level==height){
            return 1;
        }
        if (mostLevelLeft(root.right,level+1)==height){
            return 1<<(height-level)+solution(root.right,level+1,height);
        }else {
            return 1<<(height-level-1)+solution(root.left,level+1,height);
        }
    }
    private int mostLevelLeft(TreeNode root,int level){
        while (root!=null){
            level++;
            root = root.left;
        }
        return level-1;
    }


    public int findMinArrowShots(int[][] points) {
        if(points.length<2){
            return points.length;
        }
        Arrays.sort(points, (o1,o2)->{
            if (o1[1]!=o2[1]){
                return o1[1]-o2[1];
            }
            return o1[0]-o2[0];
        });
        int count = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++){
            if (points[i][0]>end){
                count++;
                end=points[i][1];
            }
        }
        return count;
    }

    /**
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid==null||grid.length==0){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < grid.length;i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j]=='1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int r, int c){
        if (r<0||c<0||r>=grid.length||c>=grid[0].length||grid[r][c]=='0'){
            return;
        }
        grid[r][c] = '0';
        dfs(grid,r+1,c);
        dfs(grid,r-1,c);
        dfs(grid,r,c-1);
        dfs(grid,r,c+1);

    }



    public int jump(int[] nums) {
        int jump_min = 1;
        if (nums.length==1){return 0;}
        int current_max_index = nums[0];
        int pre_max_index = nums[0];

        for(int i = 0; i < nums.length; i++){
            if (pre_max_index<i+nums[i]){
                pre_max_index = i+nums[i];
            }
            if(i==current_max_index&&i!=nums.length-1){
                jump_min++;
                current_max_index = pre_max_index;
            }

        }
        return jump_min;
    }
    public static boolean canJump(int[] nums) {
        if (nums.length==1){return true;}
        int[] index = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            index[i] = i + nums[i];
        }
        int max_index = 0;
        int jump = 0;
        while (max_index<nums.length-1){
            for (jump = 0; jump < nums.length; jump++){
                if(index[jump]>max_index){
                    max_index = index[jump];
                }
                if(max_index<jump){
                    return false;
                }
                if (max_index==jump&&jump!=nums.length-1){
                    return false;
                }
            }
        }
        return true;
    }
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index1 = 0,index2 = 0;
        while (index1<g.length&&index2<s.length){
            if(s[index2]>=g[index1]){
                index1++;
                index2++;
            }else {
                index2++;
            }
        }
        return index1;
    }
    public static int wiggleMaxLength(int[] nums) {
        if(nums.length<2){
            return nums.length;
        }
        //1begin 2up 3down
        int flag = 1;
        int count = 1;
        for(int i =1; i < nums.length; i++) {
            switch (flag) {
                case 1:
                    if (nums[i]>nums[i-1]){
                        flag=2;
                        count++;
                    }else if(nums[i]<nums[i-1]){
                        flag=3;
                        count++;
                    }
                    break;
                case 2:
                    if (nums[i]<nums[i-1]){
                        flag=3;
                        count++;
                    }
                    break;
                case 3:
                    if(nums[i]>nums[i-1]){
                        flag=2;
                        count++;
                    }
                    break;
            }
        }
        return count;
    }

    public String removeKdigits(String num, int k) {
        if(num.length()<=k){
            return "";
        }
        //PriorityQueue<Character> queue = new PriorityQueue<>(((o1, o2) -> {return o1-o2;}));

        LinkedList<Character> queue = new LinkedList<>();
        char[] chars = num.toCharArray();
        queue.add(chars[0]);
        for(int i = 0; i < chars.length; i++) {
            while (!queue.isEmpty() && queue.peek() > chars[i] && k > 0) {
                k--;
                queue.poll();
            }
            if (chars[i] != 0 || (chars[i] == 0 && !queue.isEmpty())) {
                queue.add(chars[i]);
            }
        }
        while (!queue.isEmpty()&&k>0){

            k--;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : queue){
            sb.append(c);
        }
        return sb.toString();
    }

    public int movingCount(int threshold, int rows, int cols) {
        int[][] array = new int[rows][cols];
        int count = 0;
        dfs(array,0,0,threshold);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(array[i][j]=='1') {
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(int[][] array, int r, int c, int threshold){
        if(r<0||c<0||r>=array.length||c>=array[0].length||compare(r)+compare(c)>threshold||array[r][c]=='1'){
            return;
        }
        array[r][c] = '1';
        dfs(array,r+1,c,threshold);
        dfs(array,r-1,c,threshold);
        dfs(array,r,c-1,threshold);
        dfs(array,r,c+1,threshold);
    }
    private int compare(int num){
        int count = 0;
        while (num!=0){
            count += num%10;
            num = num/10;
        }
        return count;
    }
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (helper(matrix,rows,cols,i,j,str,0,flag)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag){
        int index = i*cols+j;
        if (i<0||j<0||i>=rows||j>=cols||flag[index]==1||matrix[index]!=str[k]){
            return false;
        }
        if (k == str.length-1){return true;}
        flag[index] = 1;
        if (helper(matrix,rows,cols,i+1,j,str,k+1,flag)||
                helper(matrix,rows,cols,i-1,j,str,k+1,flag)||
                helper(matrix,rows,cols,i,j+1,str,k+1,flag)||
                helper(matrix,rows,cols,i,j-1,str,k+1,flag)){
            return true;
        }
        flag[index] = 0;
        return false;
    }
    public int numIslans(char[][] grid){
        if (grid==null||grid.length==0){return 0;}
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int i = 0; i < nr; i++){
            for (int j = 0; j < nc; j++){
                if (grid[i][j]=='1'){
                    num_islands++;
                    Queue<Integer> queue = new LinkedList<>();
                    grid[i][j]='0';
                    queue.add(i*nc+j);
                    while (!queue.isEmpty()){
                       int id = queue.poll();
                       int r = id/nc;
                       int c = id%nc;
                       if (r-1>=0&&grid[r][c]=='1'){
                           queue.add((r-1)*nc+c);
                           grid[r-1][c] = '0';
                       }
                        if (r+1<nr&&grid[r+1][c]=='1'){
                            queue.add((r+1)*nc+c);
                            grid[r+1][c] = '0';
                        }
                        if (c-1>=0&&grid[r][c-1]=='1'){
                            queue.add(r*nc+c-1);
                            grid[r][c-1] = '0';
                        }
                        if (c+1<nc&&grid[r][c+1]=='1'){
                            queue.add(r*nc+c+1);
                            grid[r][c+1] = '0';
                        }
                   }
                }
            }
        }
        return num_islands;
    }
    /**
     * 给定一个数组arr，全是正数;一个整数aim，求累加和等于aim的，最长子数组，要求额外空间复杂度O(1)，时间 复杂度O(N)
     */

    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums.length<=0){return 0;}
        int l = 0, r = 0;
        int len = 0;
        int sum = nums[0];
        while (r<nums.length){
            if (sum==k){
                len =Math.max(len,r-l+1);
                sum -= nums[l++];
            }else if (sum<k){
                r++;
                if (r==nums.length){break;}
                sum += nums[r];
            }else {
                sum -= nums[l++];
            }
        }
        return len;
    }
    /**
     * 给定一个数组arr，值可正，可负，可0;一个整数aim，求累加和小于等于aim的，最长子数组，要求时间复杂度O(N)
     */
    public int solution1(int[] arr, int aim){
        if (arr.length==0||arr==null){return 0;}
        int[] min_sum = new int[arr.length];
        int[] min_sum_index = new int[arr.length];
        min_sum[arr.length-1] = arr[arr.length-1];
        min_sum_index[arr.length-1] = arr.length-1;
        for (int i = arr.length-1;i>0;i--){
            if (min_sum[i]<0){
                min_sum[i-1] = min_sum[i]+arr[i-1];
                min_sum_index[i-1] = min_sum_index[i]  ;
            }else {
                min_sum[i-1] = arr[i-1];
                min_sum_index[i-1] = i-1;
            }
        }
        int R = 0, sum = 0, len = 0;
        for(int start = 0; start < arr.length; start++){
            while (R<arr.length&&sum+min_sum[R]<=aim){
                sum += min_sum_index[R];
                R = min_sum[R]+1;
            }
            //若第一个数就大于aim
            sum -= R>start ? arr[start]:0;
            len = Math.max(len,R-start);
            R = Math.max(R,start+1);
        }
        return len;
        /**
        int R = 0, sum = 0, len = 0;
        for (int i = 0;i < arr.length; i++){
            while (R<arr.length&&sum+min_sum[R]<=aim){
                sum += min_sum[R];
                R = min_sum_index[R]+1;
            }
            sum -=R>i?arr[i]:0;//减去开头位置的值
            len = Math.max(len,R-i);
            R = Math.max(R,i+1);
        }
        return len;*/
    }

    public int maxSubArray(int[] nums) {
        if(nums.length==0||nums==null){
            return 0;
        }
        int dp = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp = Math.max(dp+nums[i], nums[i]);
            result = Math.max(result, dp);
        }
        return result;
    }

    public String longestPalindrome(String s) {
        if (s.equals("")) {
            return "";
        }
        int len = s.length();
        int[][] arr = new int[len][len];
        int maxLen = 0;
        int maxEnd = 0;
        String reversStr = new StringBuilder(s).reverse().toString();
        for(int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                if (reversStr.charAt(i)==s.charAt(j)){
                    if (i==0||j==0){
                        arr[i][j] = 1;
                    }else {
                        arr[i][j] = arr[i-1][j-1]+1;
                    }
                }/**
                else {
                    arr[i][j] = 0;
                }*/
                if (arr[i][j]>maxLen){
                    /**修改的地方
                    maxLen = arr[i][j];
                    maxEnd = j;*/
                    int beforeIndex = len-1-i;//反向字符子串的原始索引
                    int firstIndex = j-arr[i][j]+1;
                    if (beforeIndex==firstIndex){
                        maxLen  = arr[i][j];
                        maxEnd = j;
                    }
                }
            }
        }
        return s.substring(maxEnd+1-maxLen,maxEnd+1);
    }




    /**174
     *      * 动态规划 如果从左上开始算 则遇到正值时就需要计算正值的盈余 因为需要累计往下计算
     *      * 如果从右下开始往上算 遇到正的盈余就不用接着往下累计 直接更新成0就好
     *      * @param dungeon
     *      * @return
    public int calculateMinimumHP(int[][] dungeon) {
     *if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
     *return 1;
     *}
     *int row = dungeon.length;
     *int col = dungeon[0].length;
     *int[][] dp = new int[row][col];
     *dp[row - 1][col - 1] = dungeon[row - 1][col - 1] >= 0 ? 0 : -dungeon[row - 1][col - 1];
     *for (int i = row - 2; i >= 0; i--) {
     *dp[i][col - 1] = Math.max(0, dp[i + 1][col - 1] - dungeon[i][col - 1]);
     *}
     *for (int i = col - 2; i >= 0; i--) {
     *dp[row - 1][i] = Math.max(0, dp[row - 1][i + 1] - dungeon[row - 1][i]);
     *}
     *for (int i = row - 2; i >= 0; i--) {
     *for (int j = col - 2; j >= 0; j--) {
     *dp[i][j] = Math.max(0, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
     *}
     *}
     *return dp[0][0] + 1;
     *}
     *
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s==null||s.length()==0||wordDict==null||wordDict.size()==0){
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 1; i <= n; i++){
            for (int j = 0; j < i; j++){
                if (dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    public int maximalSquare(char[][] matrix) {
         if (matrix==null||matrix.length==0||matrix[0].length==0||matrix[0]==null){
             return 0;
         }
         int m = matrix.length;
         int n = matrix[0].length;
         int[][] dp = new int[m][n];
         int max = 0;
         for (int i = 0; i < m; i++){
             for(int j = 0; j < n; j++){
                 dp[i][j] = matrix[i][j]=='1'?1:0;
             }
         }
        for (int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(dp[i][j]==1) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
    public int minDistance(String word1, String word2) {
        if (word1==null || word2==null){
            return 0;
        }
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int row = char1.length+1;
        int col = char2.length+1;
        int[][] dp = new int[row][col];
        //f(i,j)表示word1[:i]转换为word2[:j]需要的最少步骤。
        //(i,j)=min(f(i−1,j),f(i,j−1),f(i−1,j−1))+1  if word1[i]̸=word2[j]
        //f(i,j)=f(i−1,j−1)  if word1[i]=word2[j]
        for (int i = 0; i < row; i++){
            dp[i][0] = i;
        }
        for (int j = 0; j < col; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                if (char1[i-1]==char2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+1);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1]+1);
            }
        }
        return dp[row-1][col-1];
    }
    public static void main(String[] args) {
        int[] nums = {6, 6, 10, 1, 6, 3, 3, 5, 10, 9, 10, 6, 6, 3, 5, 2, 9, 3, 8, 6};
        System.out.println(maxSubArrayLen1(nums,15));
    }
    public static int maxSubArrayLen1(int[] arr, int aim) {
        if (arr.length<=0){return 0;}
        int l = 0, r = 0;
        int sum = arr[0];
        int len = 0;
        while (r<arr.length){
            if (aim==sum){
                len = Math.max(len, r-l+1);
                sum -= arr[l++];
            }else if (aim>sum){
                r++;
                if (r >= arr.length){break;}
                sum += arr[r];
            }else {
                sum -= arr[l++];
            }
        }
        return len;
    }







































    }
class Tower{
    int index;
    int level;
    public Tower(int index, int level){
        this.index = index;
        this.level = level;
    }
}














