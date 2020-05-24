package com.douye.linkedList;

public class LinkedList2<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev, E element, Node<E> next){
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            if (prev != null)
                stringBuilder.append(prev.element);
            stringBuilder.append("_" + element + "_");
            if (next != null)
                stringBuilder.append(next.element);

            return stringBuilder.toString();
        }
    }

    public void clear() {
        size = 0;
        first = null;
        last = null;
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
        if (index == size){
            Node<E> oldLast = last;
            last = new Node<>(oldLast ,element, null);
            if (oldLast == null){
                first = last;
            } else{
                oldLast.next = last;
            }
        }else {
            Node<E> node = node(index);
            Node<E> prev = node.prev;
            Node<E> newNode = new Node<>(prev, element, node);
            node.prev = newNode;
            if (prev == null)
                first = newNode;
            else
                prev.next = newNode;
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        E old = node.element;
        if (prev == null){
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null){
            last = prev;
        } else {
            next.prev = prev;
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
        if (index < size){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
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
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
