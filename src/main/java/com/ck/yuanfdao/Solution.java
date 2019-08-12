package com.ck.yuanfdao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    private boolean[] userd;
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] candidates) {
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