package com.douye.leetcode;

public class 剑指Offer22_链表中倒数第k个节点 {
    /**
     * 双指针 quick 和 slow 每轮都向前走一步，直至 quick 走过链表 尾节点 时跳出（跳出后，
     * slow 与尾节点距离为 k−1，即 slow 指向倒数第 k 个节点）
     *
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode quick = head;
        while (k != 0) {
            quick = quick.next;
            k--;
        }
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }
}
