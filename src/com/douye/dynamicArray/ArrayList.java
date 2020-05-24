package com.douye.dynamicArray;

public class ArrayList {
    /**
     * 元素的数量
     */
    private int size;
    /**
     * 所有的元素
     */
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capaticy) {
        // 添加判断，若小于指定的容量，则使用指定的容量创建数组
        elements = new int[capaticy < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capaticy];
    }

    public ArrayList() {
        // 默认使用指定容量创建数组
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        size = 0;
    }

    /**
     * 元素的数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(int element) {
        //elements[size++] = element;
        add(size,element);
    }

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    public int get(int index) {
        if (index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    public int set(int index, int element) {
        if (index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    public void add(int index, int element) {
        if (index<0 || index>size)
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        ensureCapacity(size+1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i-1];
        }
        size++;
        elements[index] = element;
    }

    /**
     * 删除index位置的元素
     * @param index
     * @return
     */
    public int remove(int index) {
        if (index<0 || index>size)
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        int old = elements[index];
        for (int i = index+1; i < size; i++) {
            elements[i-1] = elements[i];
        }
        size--;
        return old;
    }

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(int element) {
        for (int i=0; i<size; i++){
            if (elements[i] == element)
                return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        int[] newElement = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newElement[i] = elements[i];
        }
        elements = newElement;
        System.out.println(oldCapacity+"扩容为"+newCapacity);
    }

    @Override
    public String toString() {
        // size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);

//			if (i != size - 1) {
//				string.append(", ");
//			}
        }
        string.append("]");
        return string.toString();
    }
}
