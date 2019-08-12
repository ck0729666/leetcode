package com.ck.yuanfdao;


import java.util.*;

public class Main {


    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int i = 0, j = 0, solution = 0;
        HashSet<Character> set = new HashSet<>();
        while (i<length&&j<length){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                solution = Math.max(solution, j-i);
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return solution;
    }
    //再进行优化，找到重复字符，不需要逐渐增加i，直接跳过i-j'内
    public int length(String s){
        int n = s.length(), res = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0, j = 0; j < s.length(); j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(i,map.get(s.charAt(j)));
            }
                map.put(s.charAt(j), j+1);
                res = Math.max(res, j+1-i);

        }
        return res;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        generate(0, nums, lists, new ArrayList<Integer>());
        return lists;
    }
    //子集总和
    private void generate(int i, int[] nums,List<List<Integer>> lists, List<Integer> list){
        lists.add(new ArrayList<>(list));
        for(int j = i; j < nums.length; j++){
            list.add(nums[j]);
            generate(j+1,nums,lists,list);
            list.remove(list.size()-1);
        }
    }
    //组合总和
    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if(len==0){
            return res;
        }
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target,0,new Stack<>());
        return res;
    }
    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if(residue==0){
            res.add(new ArrayList<>(pre));
            return;
        }
        for(int i = start; i < len && residue-candidates[i]>=0;i++){
            pre.add(candidates[i]);
            findCombinationSum(residue-candidates[i],i,pre);
            pre.pop();
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> lists = new ArrayList<>();
        if(len==0){
            return res;
        }
        Arrays.sort(candidates);
        findCombinationSum2(candidates,0,len,target,new Stack<>(),res);
        return res;
    }
    private void findCombinationSum2(int[] candidates, int begin, int len, int residue, Stack<Integer> stack, List<List<Integer>> lists){
        if(residue==0){
            lists.add(new ArrayList<>(stack));
            return;
        }
        for(int i = begin; i < len && residue-candidates[i]>=0;i++){
            if(i>begin&&candidates[i]==candidates[i-1]){
                continue;
            }
            stack.add(candidates[i]);
            findCombinationSum2(candidates,i+1, len,residue-candidates[i],stack,lists);
            stack.pop();
        }
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