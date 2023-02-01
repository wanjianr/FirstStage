package com.douye.leetcode;

public class leetcode92_反转链表II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        // 开始反转节点的头节点
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        // 开始反转的节点
        head = pre.next;
        for (int i = left; i <= right; i++) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }
}
