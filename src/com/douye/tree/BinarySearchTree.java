package com.douye.tree;

import com.douye.printer.BinaryTreeInfo;
import com.douye.queue.StackQueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element){
        elementNotNullCheck(element);
        // 填加第一个节点
        if (root == null){
            root = createNode(element,null);
            size++;
            afterAdd(root);
            return;
        }
        // 添加的不是第一个节点，则需要先找到父节点
        int cmp = 0;
        Node<E> parent = root;
        Node<E> node = root;
        while (node != null){
            cmp = compare(element,node.element);
            parent = node;
            if (cmp > 0){
                // 往右找
                node = node.right;
            } else if (cmp < 0){
                // 往左找
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> newNode = createNode(element,parent);
        if (cmp > 0){
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    public void remove(E element){
        remove(getNode(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
    }
//    private void remove(Node<E> node){
//        if (node == null) return;
//        size--;
//        if (node.hasTwoChildren()){ // 该节点度为2
//            // 获取前驱结点
//            Node<E> predecessor = predecessor(node);
//            node.element = predecessor.element;
//            // 删除前驱结点（度为1或0）
//            node = predecessor;
//        }
//        // 运行到此处说明node的度为0或1
//        if (node.isLeaf()){ // 度为0
//            // node为叶子节点
//            if (node == node.parent.left){
//                node.parent.left = null;
//            }
//            if (node == node.parent.right){
//                node.parent.right = null;
//            }
//            if (node.parent == null){
//                root = null;
//            }
//            afterRemove(node);
//        } else { // 度为1
//            Node<E> child = node.left==null ? node.right : node.left;
//            if (node.parent != null){
//                child.parent = node.parent;
//                if (node == node.parent.left)
//                    node.parent.left = child;
//                else
//                    node.parent.right = child;
//            } else {
//                root = child;
//                child.parent = null;
//            }
//            afterRemove(node);
//        }
//    }

    private Node<E> getNode(E element){
        elementNotNullCheck(element);
        // 遍历二叉树
        Node<E> node = root;
        while (node != null){
            int compare = compare(element, node.element);
            if (compare == 0) return node;
            if (compare > 0) node = node.right;
            if (compare < 0) node = node.left;
        }
        return node;
    }

    public boolean contains(E element){
        return getNode(element) != null;
    }

    private int compare(E element1,E element2){
        // 默认使用Comparable
        if (comparator != null)
            return comparator.compare(element1,element2);
        return ((Comparable<E>)element1).compareTo(element2);
    }

    private void elementNotNullCheck(E element){
        if (element == null)
            throw new IllegalArgumentException("element must not be null!");
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return super.createNode(element, parent);
    }

    /**
     * 添加node之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) { }

    /**
     * 删除node之后的调整
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) { }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        toString(stringBuilder,root,"");
        return stringBuilder.toString();
    }

    private void toString(StringBuilder sb, Node<E> node, String perfix){
        if (node == null) return;

        if (node.left != null) toString(sb, node.left,perfix+"L---");
        sb.append(perfix).append(node.element).append("\n");
        if (node.right != null) toString(sb,node.right,perfix+"R---");
    }
}
