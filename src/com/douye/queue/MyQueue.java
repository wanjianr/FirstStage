package com.douye.queue;

import com.douye.linkedList.LinkedList2;
import com.douye.linkedList.List;

public class MyQueue<E> {
    private List<E> list = new LinkedList2<>();
    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }

    public void enQueue(E element){
        list.add(element);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){
        return list.get(0);
    }
}
