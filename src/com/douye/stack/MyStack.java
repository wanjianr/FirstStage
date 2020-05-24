package com.douye.stack;

import com.douye.linkedList.LinkedList2;
import com.douye.linkedList.List;

public class MyStack<E> {
    List<E> list = new LinkedList2<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size()-1);
    }

    public E peek(){
        return list.get(list.size()-1);
    }

    public void clear(){
        list.clear();
    }
}
