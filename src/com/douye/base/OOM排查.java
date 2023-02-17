package com.douye.base;

public class OOM排查 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        while(true){
            stringBuilder.append(System.currentTimeMillis());
        }
    }
}
