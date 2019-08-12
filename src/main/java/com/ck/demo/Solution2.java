package com.ck.demo;

import java.util.HashMap;

    public class Solution2 {
        //Insert one char from stringstream
        int count[] = new int[256];
        int index = 1;
         public void Insert(char ch)
         {
            if(count[ch]==0) {
                count[ch] = index++;
            } else{
                count[ch] = -1;
            }
         }

         public char first442() {
             int temp = Integer.MIN_VALUE;
             char ch='#';
             for(int i = 0; i < 256; i++) {
                 if(count[i]!=0 && count[i]!=-1 && count[i]<temp) {
                     temp = count[i];
                     ch = (char)i;
                 }
             }
             return ch;
         }
        //return the first appearence once char in current stringstream
        public char FirstAppearingOnce(String s) {
            if (s.length() == 0) {
                return '#';
            }
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    int value = map.get(s.charAt(i));
                    map.put(s.charAt(i), value + 1);
                } else {
                    map.put(s.charAt(i), 1);
                }
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1) {
                    return s.charAt(i);
                }
            }
            return '#';
        }
}