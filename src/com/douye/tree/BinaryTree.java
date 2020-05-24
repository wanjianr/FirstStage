package com.douye.tree;

import com.douye.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }

    public static abstract class Visitor<E>{
        boolean stop;
        protected abstract boolean visit(E element);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    // 前序遍历
    public void preorderTraversal(Visitor<E> visitor){
        if (visitor == null) return;
        preorderTraversal(root,visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }

    // 中序遍历
    public void inorderTraversal(Visitor<E> visitor){
        if (visitor == null) return;
        inorderTraversal(root,visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }

    // 后续遍历
    public void postorderTraversal(Visitor<E> visitor){
        if (visitor == null) return;
        postorderTraversal(root,visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor){
        if (node == null || visitor.stop) return;
        postorderTraversal(node.left,visitor);
        postorderTraversal(node.right,visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    // 层序遍历
    public void levelOrderTraversal(Visitor<E> visitor){
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (visitor.stop) return;
            visitor.stop = visitor.visit(node.element);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    // 获取数的高度
    public int height(){
        if (root == null) return 0;
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int levelSize = 1; // 记录每一层的节点数
        while (!queue.isEmpty()){
            node = queue.poll();
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (levelSize == 0){
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
    // 递归实现
    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(Node<E> node){
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left),getHeight(node.right));
    }

    // 是否为完全二叉树
    public boolean isComplete(){
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        Node<E> node = root;
        queue.offer(node);
        boolean isLeaf = false;
        while (!queue.isEmpty()){
            node = queue.poll();
            if (isLeaf && !node.isLeaf()){
                // 说明叶子节点后还存在叶子节点
                return false;
            }
            if (node.left != null)
                queue.offer(node.left);
            else if (node.right != null)
                return false; // 有右子节点
            if (node.right != null)
                queue.offer(node.right);
            else
                isLeaf = true; // 遇到只有左子节点的节点
        }
        return true;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    // 获取节点的前驱节点
    protected Node<E> predecessor(Node<E> node){
        if (node == null) return node;  //无前驱节点

        if (node.left != null){
            node = node.left;
            while (node.right != null){
                node = node.right;
            }
            return node;
        }
        while (node.parent != null && node == node.parent.left){
            node = node.parent;
        }
        return node.parent;
    }

    // 获取节点的后驱节点
    protected Node<E> successor(Node<E> node){
        if (node == null) return null;  //无前驱节点

        if (node.right != null){
            node = node.right;
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }
        return node.parent;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }
//    @Override
//    public Object string(Object node) {
//        Node<E> myNode = (Node<E>) node;
//        String parentNode = "null";
//        if (myNode.parent != null)
//            parentNode = myNode.parent.element.toString();
//        return myNode.element + "_p{" + parentNode + "}";
//    }
}
