package com.douye.queue;

import com.douye.linkedList.LinkedList2;
import com.douye.linkedList.List;

import java.util.Stack;

public class StackQueue<E> {
    private Stack<E> inStack = new Stack<>();
    private Stack<E> outStack = new Stack<>();
    public int size(){
        return inStack.size();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void clear(){
        inStack.clear();
    }

    public void enQueue(E element){
        inStack.push(element);
    }

    public E deQueue(){
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public E front(){
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

//    /** Initialize your data structure here. */
//    public MyQueue() {
//        Stack<Integer> inStack = new Stack<>();
//        Stack<Integer> outStack = new Stack<>();
//    }
//
//    /** Push element x to the back of queue. */
//    public void push(int x) {
//        inStack.push(x);
//    }
//
//    /** Removes the element from in front of queue and returns that element. */
//    public int pop() {
//        while (!inStack.isEmpty()){
//            outStack.push(inStack.pop());
//        }
//        return outStack.pop();
//    }
//
//    /** Get the front element. */
//    public int peek() {
//        while (!inStack.isEmpty()){
//            outStack.push(inStack.pop());
//        }
//        return outStack.peek();
//    }
//
//    /** Returns whether the queue is empty. */
//    public boolean empty() {
//        return inStack.isEmpty() && outStack.isEmpty();
//    }
}
