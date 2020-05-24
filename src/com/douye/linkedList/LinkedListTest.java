package com.douye.linkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList2<Integer> list = new LinkedList2<>();
        list.add(22);
        list.add(33);
        list.add(44);
        list.add(55);
        System.out.println(list);

        list.add(2,2);
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
    }
}
