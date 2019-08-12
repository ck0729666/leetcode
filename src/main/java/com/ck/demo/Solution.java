package com.ck.demo;

import java.util.HashMap;

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] d = new int[len][len];
        for(int i = len-1; i >= 0; i--) {
            d[i][i] = 1;
            for(int j = i+1;j < len; j++) {
                if(s.charAt(i) != s.charAt(j)) {
                    d[i][j] = Math.max(d[i][j-1],d[i+1][j]);
                } else {
                    d[i][j] = d[i+1][j-1] + 2;
                }
            }
        }
        return d[0][len-1];
    }
    public int FirstChar(String str) {
        int len = str.length();
        if(len == 0) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            if(map.containsKey(str.charAt(i))) {
                int value = map.get(str.charAt(i));
                map.put(str.charAt(i), value+1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for(int i = 0; i < str.length(); i++) {
            if(map.get(str.charAt(i))==1) {
                return i;
            }
        }
        return -1;
    }
    public String reverseDanci(String str) {
        //trim()删除头尾空白符的字符串
        if(str.trim().length()==0) {
            return str.trim();
        }
        String[] temp = str.trim().split(" +");
        String res = "";
        for(int i = temp.length-1; i > 0; i--) {
            res += temp[i] + " ";
        }
        return res + temp[0];
    }
    public boolean rotateString(String A, String B) {
        return A.length()==B.length() && (A+A).contains(B);
    }
    public String LeftRotateString(String str ,int n) {
        int len = str.length();
        if (len == 0) {
            return "";
        }
        n = n % len;
        String s1 = str.substring(n, len);
        String s2 = str.substring(0, n);
        return s1+s2;
    }
    public String reverseString(String s) {
        if(s.length() < 2) {
            return s;
        }
        int l = 0, r = s.length()-1;
        char[] strs = s.toCharArray();
        while(l < r) {
            char temp = strs[l];
            strs[l] = strs[r];
            strs[r] = temp;
            l++;
            r--;
        }
        return new String(strs);
    }
    public int StrToInt(String str) {
        if(str.length()==0) {
            return 0;
        }
        int flag = 0;
        if(str.charAt(0)=='+') {
            flag = 1;
        } else if(str.charAt(0)=='-') {
            flag = 2;
        }
        int start = flag > 0 ? 1 : 0;
        int result = 0;
        while(start < str.length()) {
            if(str.charAt(start) > '0' || str.charAt(start) < '0') {
                return 0;
            }
            result = result*10 + (str.charAt(start)-'0');
            start++;
        }
        return flag == 2 ? result : -result;
    }
    public int firstZifu(String str) {
        int len = str.length();
        if(len == 0) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            if(map.containsKey(str.charAt(i))) {
                int value = map.get(str.charAt(i));
                map.put(str.charAt(i), value+1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for(int i = 0; i < len; i++) {
            if(map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
    public boolean isNumber(char[] str) {
        int len = str.length;
        boolean sign = false, decimal = false, hasE = false;
        for(int i = 0; i < len; i++) {
            if(str[i] == '+' || str[i] == '-') {
                if(!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E') {
                    return false;
                }
                if(sign && str[i-1]!='e'&&str[i-1]!='E') {
                    return false;
                }
                sign = true;
            } else if(str[i] == 'e' || str[i] == 'E') {

            }
        }
        return true;
    }
























}
