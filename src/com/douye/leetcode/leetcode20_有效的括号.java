package com.douye.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class leetcode20_有效的括号 {

    public static void main(String[] args) {
        String s = "(";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for(int i=0; i< s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else if (stack.peek() != map.get(s.charAt(i))) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
