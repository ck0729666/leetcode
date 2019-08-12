package com.ck.demo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class solution3 {

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(str));

    }

    public static boolean isPalindrome(String s) {
        int len  = s.length();
        if(len == 0) {
            return true;
        }
        int l = 0;
        int r = s.length()-1;
        while(l < r) {
            /**
             while(!Character.isLetterOrDigit(s.charAt(l))) {
             l++;
             }
             while(!Character.isLetterOrDigit(s.charAt(r))) {
             r--;
             }
             char c1 = s.charAt(l);
             Character char1 = Character.toLowerCase(c1);
             char c2 = s.charAt(r);
             Character.toLowerCase(c2);
             Character char2 = Character.toLowerCase(c2);

             if(!char1.equals(char2)) {
             return false;
             }
             l++;
             r--;
             */


            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;

            }

        }
        return  true;
    }
    private int index, len;
    public void HH(String s, int l, int r) {
        while(l >= 0 && r <= s.length() && s.charAt(l)==s.charAt(r)) {
            l--;
            r++;
        }
        if(len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }
    public String longestHH(String s) {
        if(s.length() < 2) {
            return s;
        }
        for(int i = 0; i < s.length()-1; i++) {
            HH(s, i, i);
            HH(s, i,i+1);
        }
        return s.substring(index, index+len);
    }
    public int longestSub(String s) {
        int len = s.length();
        if(len == 0) {
            return 0;
        }
        //
        // int count = 1;
        int[][] d = new int[len][len];
        for(int i = len-1; i >= 0; i--) {
            d[i][i] = 1;
            for(int j = i+1; j < len; j++) {
                if(s.charAt(i)==s.charAt(j)) {
                    d[i][j] = d[i+1][j-1] + 2;
                } else {
                    d[i][j] = Math.max(d[i][j-1],d[i+1][j]);
                }
            }
        }
        return d[0][len-1];
    }
    ArrayList<String> res = new ArrayList<>();
    public ArrayList<String> Persss(String str) {
        if(str == null) {
            return res;
        }
        Helper(str.toCharArray(), 0);
        Collections.sort(res);
        return res;
    }
    public void Helper(char[] str, int i) {
        int len = str.length;
        if(i == str.length-1) {
            res.add(String.valueOf(str));
        } else {
            for(int j = i; j < str.length; j++) {
                if(j!=i && str[i]==str[j]) {
                    continue;
                }
                swap(str, i, j);
                Helper(str,i+1);
                swap(str, i, j);
            }
        }
    }
    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }































}
