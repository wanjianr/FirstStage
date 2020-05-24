package com.douye.linkedList;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    public void clear() {
        size = 0;
        first = null;
    }

    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0){
            first = new Node<>(element,first);
        }else {
            Node<E> pre = node(index-1);
            pre.next = new Node<>(element,pre.next);
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        E old = node.element;
        if (index == 0){
            first = node.next;
        }else {
            Node<E> pre = node(index-1);
            pre.next = node.next;
        }
        size--;
        return old;
    }

    public int indexOf(E element) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (node.element == element)
                return i;
            node = node.next;
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     */
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        Node<E> node = first;
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
