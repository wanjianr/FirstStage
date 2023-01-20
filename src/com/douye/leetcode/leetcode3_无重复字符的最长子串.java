package com.douye.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leetcode3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
        int[] res = new int[2];
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i< s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                left = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
