package com.douye.leetcode;

import java.util.Stack;

public class leetcode150 {
    public static void main(String[] args) {
        String[] s = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int i = evalRPN(s);
        System.out.println(i);
    }
    public static int evalRPN(String[] tokens) {
        String flag = "+-*/";
        Stack<Integer> stack = new Stack<>();
        for (String s:tokens) {
            if (!flag.contains(s)){
                int num = Integer.parseInt(s);
                stack.push(num);
            } else {
                int n1 = stack.pop();
                int n2 = stack.pop();
                if (s == "+") stack.push(n2+n1);
                if (s == "-") stack.push(n2-n1);
                if (s == "*") stack.push(n2*n1);
                if (s == "/") stack.push(n2/n1);
            }
        }
        return stack.pop();
    }
}
