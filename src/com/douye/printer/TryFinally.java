package com.douye.printer;

public class TryFinally {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        try {
            int i = 2;
            return 1 * 1;
        } finally {
            return 5;
        }
    }
}
