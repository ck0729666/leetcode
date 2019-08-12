package wangyi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char c = '0';
        System.out.println(Integer.valueOf(c));
    }
    public int numDecodings1(String s) {
        HashMap<Integer,Integer> memoization = new HashMap<>();
        return solution(s,0,memoization);
    }
    //1.递归2.优化递归，把走过的保存下来，加个map
    //?递归时候会重复走
    private int solution(String s, int start, HashMap<Integer,Integer> memoization){
        if (start==s.length()){return 1;}
        if (s.charAt(start)==0){return 0;}
        int m = memoization.getOrDefault(start,-1);
        if (m!=-1){return m;}
        int ans1 = solution(s,start+1,memoization);
        int a = s.charAt(start)-'0';
        int b = s.charAt(start+1)-'0';
        int ans2 = 0;
        if (start<s.length()-1) {
            if ((a * 10 + b) <= 26) {
                ans2 = solution(s, start + 2,memoization);
            }
        }
        memoization.put(start,ans1+ans2);
        return ans1+ans2;
    }
    //动态规划  91
    public int numDecodings(String s) {
        int len = s.length();
        //int[] dp = new int[len+1];
        int[] dp = new int[3];
        if (s.charAt(len-1)!='0'){
            //dp[len-1] = 1;
            dp[(len-1)%3] = 1;
        }
        //dp[len] = 1;
        dp[len%3] = 1;
        for (int i = len-2; i>=0; i--){
            if (s.charAt(i)=='0'){
                dp[i%3]=0;//置为0，不然空间复用，用上次循环到这的值
                continue;
            }
            //int ans1 = dp[i+1];
            int ans1 = dp[(i+1)%3];
            int ans2 = 0;
            int a = s.charAt(i)-'0';
            int b = s.charAt(i+1)-'0';
            if (a*10+b <= 26){
                //ans2 = dp[i+2];
                ans2 = dp[(i+2)%3];
            }
           //dp[i] = ans1+ans2;
            dp[i%3] = ans1+ans2;
        }
        return dp[0];
    }





















    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0||nums==null){ return nums; }
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target+1)-1;
        if (first==nums.length||first>last){
            return new int[]{-1,-1};
        }
        return new int[]{first, Math.max(first,last)};
    }
    private int binarySearch(int[] nums, int target){
        int l = 0, r = nums.length;
        while (l<r){
            int mid = l + (r-l)/2;
            if (nums[mid]>=target){
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    /**
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int size = len1+len2;
        if (size%2==1){
            return findKth(nums1,0,len1,nums2,0,len2,size/2+1);
        }else {
            return (findKth(nums1,0,len1,nums2,0,len2,size/2)+findKth(nums1,0,len1,nums2,0,len2,size/2+1))/2;
        }
    }
    private double findKth(int[] nums1, int start1, int len1, int[] nums2, int start2, int len2, int k){
        if (len1-start1>len2-start2){
            return findKth(nums2,start2,len2,nums1,start1,len1,k);
        }
        if (len1-start1==0){
            return nums2[k-1];
        }
        if (k==1){
            return Math.min(nums1[start1],nums2[start2]);
        }
        int p1 = start1+Math.min(len1-start1,k/2);
        int p2 = start2+k-p1+start1;

    }*/
























}
