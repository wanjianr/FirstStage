package com.douye.tree;

import com.douye.printer.BinaryTreeInfo;
import com.douye.printer.BinaryTrees;

import java.util.Comparator;

public class AVLTreeTest {
    static void test1(){
        int data = 0;
        AVLTree<Integer> avlTree = new AVLTree<>();
        Integer[] num = new Integer[] { 13, 14, 15, 12, 11, 17, 16, 8, 9, 1};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
//        for (int i = 0; i < 30; i++) {
//            data = (int)(Math.random()*100);
//            System.out.print(data+",");
//            avlTree.add(data);
//            binarySearchTree.add(data);
//        }
        for (int i = 0; i < num.length; i++) {
            avlTree.add(num[i]);
            binarySearchTree.add(num[i]);
        }
        System.out.println();
        BinaryTrees.println(avlTree);
        avlTree.remove(16);
        System.out.println();
        BinaryTrees.println(avlTree);
        System.out.println();
        BinaryTrees.println(binarySearchTree);
    }
    public static void main(String[] args) {
        test1();
    }
}
