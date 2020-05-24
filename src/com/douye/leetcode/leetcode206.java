package com.douye.leetcode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * Definition for singly-linked list.
 */

public class leetcode206 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newListNode = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newListNode;
    }
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newListNode = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = newListNode;
            newListNode = head;
            head = tmp;
        }
        return newListNode;
    }
}
