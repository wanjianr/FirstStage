package com.douye.set;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int i = list.indexOf(element);
        if (i != -1){
            list.set(i,element);
        } else {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int i = list.indexOf(element);
        if (i != -1)
            list.remove(i);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }
}
