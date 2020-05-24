package com.douye.map;

import com.douye.tree.BinaryTree;
import com.douye.tree.RBTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K,V> implements Map<K,V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Comparator<K> comparator;

    protected int size;
    protected Node<K,V> root;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        elementNotNullCheck(key);
        // 填加第一个节点
        if (root == null){
            root = new Node<>(key, value, null);
            size++;
            afterPut(root);
            return null;
        }
        // 添加的不是第一个节点，则需要先找到父节点
        int cmp = 0;
        Node<K,V> parent = root;
        Node<K,V> node = root;
        while (node != null){
            cmp = compare(key,node.key);
            parent = node;
            if (cmp > 0){
                // 往右找
                node = node.right;
            } else if (cmp < 0){
                // 往左找
                node = node.left;
            } else {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        Node<K,V> newNode = new Node<>(key,value,parent);
        if (cmp > 0){
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(getNode(key));
    }

    private V remove(Node<K,V> node){
        if (node == null) return null;

        size--;
        V oldValue = node.value;
        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K,V> s = predecessor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;

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
        return oldValue;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;
        Node<K,V> node = root;
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            node = queue.poll();
            if (valEquals(value,node.value)) return true;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;

        traversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    private static class Node<K,V> {
        boolean color = RED; // 默认节点为红色
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key, V value, Node<K,V> parent) {
            this.key = key;
            this.value = value;
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

        public Node<K,V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    private void elementNotNullCheck(K key){
        if (key == null)
            throw new IllegalArgumentException("element must not be null!");
    }

    private boolean valEquals(V val1, V val2) {
        return val1 == null ? val2 == null : val1.equals(val2);
    }

    protected void afterPut(Node<K,V> node) {
        Node<K,V> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)){
            return;
        }

        Node<K,V> uncle = parent.sibling();
        Node<K,V> grand = red(parent.parent);
        if (isRed(uncle)){
            // 叔父节点为红色
            black(parent);
            black(uncle);
            afterPut(grand);
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
    }

    protected void afterRemove(Node<K,V> node) {
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<K,V> parent = node.parent;
        if (parent == null) return;

        boolean left = parent.left == null || node.isLeftChild();
        Node<K,V> sibling = left ? parent.right : parent.left;
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

    protected Node<K,V> predecessor(Node<K,V> node){
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

    private int compare(K key1,K key2){
        // 默认使用Comparable
        if (comparator != null)
            return comparator.compare(key1,key2);
        return ((Comparable<K>)key1).compareTo(key2);
    }

    private Node<K,V> getNode(K key){
        elementNotNullCheck(key);
        // 遍历二叉树
        Node<K,V> node = root;
        while (node != null){
            int compare = compare(key, node.key);
            if (compare == 0) return node;
            if (compare > 0) node = node.right;
            if (compare < 0) node = node.left;
        }
        return node;
    }

    private Node<K,V> color(Node<K,V> node, boolean color) {
        if (node == null) return node;
        ((Node<K,V>)node).color = color;
        return node;
    }

    protected void rotateLeft(Node<K,V> grand){
        Node<K,V> parent = grand.right;
        Node<K,V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);
    }

    protected void rotateRight(Node<K,V> grand){
        Node<K,V> parent = grand.left;
        Node<K,V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }

    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child){
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

    private Node<K,V> red(Node<K,V> node) {
        return color(node,RED);
    }

    private Node<K,V> black(Node<K,V> node){
        return color(node,BLACK);
    }

    private boolean colorOf(Node<K,V> node){
        return node == null ? BLACK : ((Node<K,V>)node).color;
    }

    private boolean isRed(Node<K,V> node){
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<K,V> node){
        return colorOf(node) == BLACK;
    }
}
