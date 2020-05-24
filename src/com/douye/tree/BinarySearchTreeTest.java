package com.douye.tree;

import com.douye.printer.BinaryTreeInfo;
import com.douye.printer.BinaryTrees;

import java.util.Comparator;

public class BinarySearchTreeTest {

    static void test1(){
        Integer data[] = new Integer[]{7,4,9,2,5,8,11,3,12,1};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer-t1;
            }
        });
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println();
        binarySearchTree.remove(1);
        binarySearchTree.remove(3);
        binarySearchTree.remove(12);
        BinaryTrees.println(binarySearchTree);
    }

    static void test2(){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            binarySearchTree.add((int)(Math.random()*100));
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println(binarySearchTree.getHeight());
        System.out.println(binarySearchTree.getHeight());
    }

    // 打印树
    static void test3(){
        BinaryTrees.println(new BinaryTreeInfo() {
            @Override
            public Object root() {
                return "douye";
            }

            @Override
            public Object left(Object node) {
                if (node.equals("douye")) return "dou";
                if (node.equals("dou")) return "A";
                return null;
            }

            @Override
            public Object right(Object node) {
                if (node.equals("douye")) return "ye";
                if (node.equals("dou")) return "B";
                return null;
            }

            @Override
            public Object string(Object node) {
                return node;
            }
        });
    }

    // 测试前序、中序、后序、层序方法
    static void test4(){
        Integer data[] = new Integer[]{7,4,9,2,5};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println(binarySearchTree);
        System.out.println(binarySearchTree.isComplete());

        //binarySearchTree.levelOrderTraversal();
    }

    // 自定义接口实现前序、中序、后序、层序方法
    static void test5(){
        Integer data[] = new Integer[]{7,4,9,2,5,8,11,1,3,10,12};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }
        BinaryTrees.println(binarySearchTree);
        binarySearchTree.preorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                if (element == 3) return true;
                System.out.print(element+" ");
                return false;
            }
        });
        System.out.println();
        binarySearchTree.inorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                if (element == 5) return true;
                System.out.print(element+" ");
                return false;
            }
        });
        System.out.println();
        binarySearchTree.postorderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                if (element == 4) return true;
                System.out.print(element+" ");
                return false;
            }
        });
        System.out.println();
        binarySearchTree.levelOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                if (element == 5) return true;
                System.out.print(element+" ");
                return false;
            }
        });
    }
    public static void main(String[] args) {
        test5();
    }
}
