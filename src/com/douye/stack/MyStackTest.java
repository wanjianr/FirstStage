package com.douye.stack;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(11);
        myStack.push(22);
        myStack.push(33);

        while (!myStack.isEmpty()){
            System.out.println(myStack.pop());
        }
    }
}
