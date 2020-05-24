package com.douye.queue;

public class MyQueueTest {
    public static void main(String[] args) {
        CircleQueue<Integer> myQueue = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            myQueue.enQueue(i);
        }
        System.out.println(myQueue);
        for (int i = 0; i < 5; i++) {
            myQueue.deQueue();
        }
        System.out.println(myQueue);
        for (int i = 15; i < 20; i++) {
            myQueue.enQueue(i);
        }
        System.out.println(myQueue);
        for (int i = 30; i < 35; i++) {
            myQueue.enQueue(i);
        }
        System.out.println(myQueue);
        while (! myQueue.isEmpty()){ // 22 11 33 44
            System.out.println(myQueue.deQueue());
        }
    }
}
