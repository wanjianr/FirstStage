package com.douye.tree;

import java.util.Comparator;

public class RBTree<E> extends BalanceBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED; // 默认节点为红色
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) str = "R_";
            return str + element.toString();
        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node,RED);
    }

    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)){
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)){
            // 叔父节点为红色
            black(parent);
            black(uncle);
            afterAdd(grand);
            return;
        }

        // 叔父节点不为红色
        if (parent.isLeftChild()){
            if (node.isLeftChild()){
                black(parent);
            } else  {// LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()){ // RL
                black(node);
                rotateRight(parent);
            } else {
                black(parent);
            }
            rotateLeft(grand);
        }
//        afterRotate(grand, parent, node);
    }


    @Override
    protected void afterRemove(Node<E> node) {
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        if (parent == null) return;

        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更新兄弟节点
                sibling = parent.right;
            }

            // 兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色子节点，父节点向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) afterRemove(parent);
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) { // 可以认为左边为null（默认为black），红子节点在右边
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更新兄弟节点
                sibling = parent.left;
            }

            // 兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色子节点，父节点向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) afterRemove(parent);
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) { // 可以认为左边为null（默认为black），红子节点在右边
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }


}
