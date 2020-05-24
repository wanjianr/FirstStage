package com.douye.dynamicArray;

public class Main {
    public static void main(String[] args) {
        ArrayList1<Object> list = new ArrayList1<>();
        Person person1 = new Person("Jian", 22);
        Person person2 = new Person("Tom", 21);
        Person person3 = new Person("Jenny", 22);
        list.add(person1);
        list.add(null);
        list.add(person2);
        list.add(22);
        list.add(22.1);
        list.add(person3);
        System.out.println(list);
        System.out.println(list.indexOf(person2));
    }
}
