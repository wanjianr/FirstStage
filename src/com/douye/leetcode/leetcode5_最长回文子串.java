package com.douye.leetcode;

public class leetcode5_最长回文子串 {
    public static void main(String[] args) {
        String s = "aba";
        String palindrome = longestPalindrome(s);
        System.out.println(palindrome);
    }

    /**
     * 中心扩散思想，通过便利字符串，对各个字符串进行中心遍历
     *        1. 每一次中心遍历都需要重新初始化回文串长度、中心字符串左、右字符的坐标
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        int resLen = 1;
        int resLeft = 0;
        for(int i = 0; i < len; i++) {
            // 1. 每一次中心遍历都需要重新初始化回文串长度、中心字符串左、右字符的坐标
            int maxLen = 1;
            int l = i -1;
            int r = i + 1;
            // 2. 循环，匹配出左边与中心字符相同的最左边的坐标 l
            while(l >= 0 && s.charAt(i) == s.charAt(l)) {
                maxLen++;
                l--;
            }
            // 3. 循环，匹配出右边与中心字符相同的最右边的坐标 r
            while(r < len && s.charAt(i) == s.charAt(r)) {
                maxLen++;
                r++;
            }
            // 4. 之后匹配中心字符两边字符相同的字符，并跟新坐标
            while(l>=0 && r<len && s.charAt(l) == s.charAt(r)) {
                maxLen+=2;
                l--;
                r++;
            }
            // 5. 如果本次遍历得到的回文串长度比之前的长，则更新长度与回文串的起始坐标
            if(maxLen > resLen) {
                resLen = maxLen;
                resLeft = l;
            }
        }
        return s.substring(resLeft+1, resLeft + resLen+1);
    }
}
