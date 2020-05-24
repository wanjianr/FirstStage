package com.douye.map;

import com.douye.printer.BinaryTreeInfo;
import com.douye.printer.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap_v1<K,V> implements Map<K,V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private int size = 0;

    public HashMap_v1() {
        table = new Node[DEFAULT_CAPACITY];
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
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        // 根据key获取索引
        int index = index(key);
        Node<K,V> root = table[index];
        // 没有找到相同的key
        if (root == null) {
            root = new Node<>(key,value,null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }
        // 添加的不是第一个节点，则需要先找到父节点
        int cmp = 0;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K,V> result = null;
        Node<K,V> parent = root;
        Node<K,V> node = root;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1,k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null &&
                        k1.getClass() == k2.getClass() &&
                        k1 instanceof Comparable &&
                        (cmp = ((Comparable)k1).compareTo(k2)) != 0) {

            } else {
                if ((node.left != null && (result = node(node.left,k1)) != null) ||
                        (node.right != null && (result = node(node.right,k1)) != null)) {
                    node = result;
                    cmp = 0;
                } else {
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0){
                // 往右找
                node = node.right;
            } else if (cmp < 0){
                // 往左找
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = k1;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);

        Node<K,V> newNode = new Node<>(k1,value,parent);
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
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K,V> node){
        if (node == null) return null;
        size--;
        V oldValue = node.value;
        if (node.hasTwoChildren()){ // 该节点度为2
            // 获取前驱结点
            Node<K,V> predecessor = predecessor(node);
            node.key = predecessor.key;
            node.value = predecessor.value;
            node.hash = predecessor.hash;
            // 删除前驱结点（度为1或0）
            node = predecessor;
        }
        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
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
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;
        Node<K, V> node = null;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (node.value == value) return true;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;
        Node<K, V> node = null;
        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (visitor.visit(node.key,node.value)) return;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
    }

    public void print() {
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【index = " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object string(Object node) {
                    return node;
                }

                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>)node).right;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>)node).left;
                }
            });
            System.out.println("---------------------------------------------------");
        }
    }

    private int index(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length-1);
    }

    private int index(Node<K,V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length-1);
    }

//    private int compare(K key1,K key2, int h1, int h2){
//        int result = h1 - h2;
//        if (result != 0) return result;
//
//        // 哈希值相等，则比较equals()
//        if (Objects.equals(key1,key2)) return 0;
//
//        // 哈希值相同，equals()不同
//        if (key1 != null && key2 != null) {
//            String key1Class = key1.getClass().getName();
//            String key2Class = key2.getClass().getName();
//            result = key1Class.compareTo(key2Class);
//            if (result != 0) return result;
//
//            // 同一类型并具备可比较性
//            if (key1 instanceof Comparable){
//                return ((Comparable) key1).compareTo(key2);
//            }
//        }
//        // 同一类型，哈希值相等，但不具备可比较性
//        // key1 != null key2 == null
//        // key1 == null key2 != null
//        return System.identityHashCode(key1) - System.identityHashCode(key2);
//    }

    private void afterPut(Node<K,V> node) {
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

    private void afterRemove(Node<K,V> node) {
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

    private Node<K,V> predecessor(Node<K,V> node){
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

    private Node<K,V> node(K key){
        Node<K,V> root = table[index(key)];
        return node(root, key);
    }

    private Node<K,V> node(Node<K,V> node, K k1){
        // 遍历二叉树
        Node<K,V> result = null;
        int compare = 0;
        int h1 = k1 == null ? 0 : k1.hashCode();
        while (node != null){
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1,k2)) {
                return node;
            } else if (k1 != null && k2 != null &&
                        k1.getClass() == k2.getClass() &&
                        k1 instanceof Comparable &&
                        (compare = ((Comparable)k1).compareTo(k2)) != 0) {
                node = compare > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right,k1)) != null) {
                return result;
            } else { // 只能往左找
                node = node.left;
            }
//            } else if (node.left != null && (result = node(node.left,k1)) != null) {
//                node = result;
//            } else {
//                return null;
//            }
        }
        return null;
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
            table[index(grand.key)] = parent;
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

    private static class Node<K,V> {
        boolean color = RED; // 默认节点为红色
        int hash;
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key, V value, Node<K,V> parent) {
            this.key = key;
            this.hash = key == null ? 0 : key.hashCode();
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

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }
}
