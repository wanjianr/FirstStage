package com.douye.tree;

import java.util.Comparator;

public class BalanceBST<E> extends BinarySearchTree<E> {
    public BalanceBST(){
        this(null);
    }

    public BalanceBST(Comparator<E> comparator){
        super(comparator);
    }

    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);
    }

    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        if (grand.isLeftChild()){ // grand为左子节点
            grand.parent.left = parent;
        } else if (grand.isRightChild()){ // grand为右子节点
            grand.parent.right = parent;
        } else { // 为根节点
            root = parent;
        }
        parent.parent = grand.parent;

        if (child != null) child.parent = grand;
        grand.parent = parent;
    }
}
