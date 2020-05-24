package com.douye.set;

public class SetTest {
    static void test1(){
        Set<Integer> mySet = new ListSet<>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(2);
        mySet.add(3);
        mySet.add(4);
        mySet.add(4);


        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(3);
        treeSet.add(12);
        treeSet.add(9);
        treeSet.remove(3);
        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void main(String[] args) {
        test1();
    }
}
