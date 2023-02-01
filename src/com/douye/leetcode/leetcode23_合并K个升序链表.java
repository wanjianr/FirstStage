package com.douye.leetcode;

public class leetcode23_合并K个升序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 1) return null;
        return mergeList(lists, 0, lists.length-1);
    }

    public ListNode mergeList(ListNode[] listNodes, int left, int right) {
        if (left == right) return listNodes[left];
        int mid = (left + right) / 2;
        ListNode listNode1 = mergeList(listNodes, left, mid);
        ListNode listNode2 = mergeList(listNodes, mid + 1, right);
        return mergeTwo(listNode1, listNode2);
    }

    // 合并两个有序链表
    public ListNode mergeTwo(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) return listNode2;
        if (listNode2 == null) return listNode1;
        if (listNode1.val < listNode2.val) {
            listNode1.next = mergeTwo(listNode1.next, listNode2);
            return listNode1;
        } else {
            listNode2.next = mergeTwo(listNode1, listNode2.next);
            return listNode2;
        }
    }
}
