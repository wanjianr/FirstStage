package com.douye.leetcode;

public class leetcode82_删除排序链表中的重复元素II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode move = cur;
                while (move != null && cur.val == move.val) {
                    move = move.next;
                }
                pre.next = move;
                cur = move;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
        } else {
            ListNode move = head;
            while (move != null && head.val == move.val) {
                move = move.next;
            }
            return deleteDuplicates(move);
        }
        return head;
    }
}
