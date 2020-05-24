package com.douye.queue;

public class CircleQueue<E> {
    private int front = 0;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] element;

    public CircleQueue() {
        element = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            element[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    public void enQueue(E elements){
        ensureCapacity(size+1);
        element[index(size)] = elements;
        size++;
    }

    public E deQueue(){
        E deNum = element[front];
        element[front] = null;
        front = index(1);
        size--;
        return deNum;
    }

    public E front(){
        return element[front];
    }

    private int index(int index){
        return (front + index) % element.length;
//        index += front;
//        return index - ((index>element.length) ? element.length : 0);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = element.length;
        if (oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElement = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElement[i] = element[index(i)];
        }
        element = newElement;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(element.length).append(" size=").append(size).append(" front=").append(front).append(", [");
        for (int i = 0; i < element.length; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(element[i]);
        }
        string.append("]");
        return string.toString();
    }
}
