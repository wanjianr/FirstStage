package com.douye.leetcode;

public class leetcode143_重排链表 {
    public void reorderList(ListNode head) {
        reorder(head);
    }
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode preTail = dummy;
        ListNode tail = head;
        while (tail.next != null) {
            preTail = tail;
            tail = tail.next;
        }
        ListNode headNext = head.next;
        head.next = preTail.next;
        preTail.next = null;
        tail.next = reorder(headNext);
        return head;
    }
}
