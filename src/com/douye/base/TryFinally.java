package com.douye.base;

public class TryFinally {
    public static void main(String[] args) {
        try {
            Integer l1 = new Integer(100);
            Integer l2 = 100;
            System.out.println(l1 == l2);
            throw new Exception("exception");
        } catch (Exception e) {
            System.out.println("exception");
        } finally {
            System.out.println("finally");
        }
    }
}
