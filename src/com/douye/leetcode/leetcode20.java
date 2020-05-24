package com.douye.leetcode;

import com.douye.stack.MyStack;

import java.util.HashMap;

public class leetcode20 {
    public static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('[',']');
        map.put('{','}');
        map.put('(',')');
    }

    public static void main(String[] args) {
        String s = "[[[{}}]]]";
        boolean valid1 = isValid4(s);
        System.out.println(valid1);
    }

    // 非常耗时
    public static boolean isValid1(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")){
            if (s.contains("()")) s.replace("()","");
            if (s.contains("[]")) s.replace("[]","");
            if (s.contains("{}")) s.replace("{}","");
        }
        return s.isEmpty();
    }

    // 内存37.1M
    public static boolean isValid2(String s) {
        MyStack<Character> stack = new MyStack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '('){
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                else {
                    if (stack.peek() == '[' && c == ']'
                        || stack.peek() == '{' && c == '}'
                        || stack.peek() == '(' && c == ')')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 内存37.2M
    public static boolean isValid3(String s) {
        MyStack<Character> stack = new MyStack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '('){
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char left = stack.pop();
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
                if (left == '(' && c != ')') return false;
            }
        }
        return stack.isEmpty();
    }

    // isValid3的优化版
    public static boolean isValid4(String s) {
        MyStack<Character> stack = new MyStack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char left = stack.pop();
                if (c != map.get(left)) return false;
            }
        }
        return stack.isEmpty();
    }
}
