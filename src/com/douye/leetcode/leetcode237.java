package com.douye.leetcode;
/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * Definition for singly-linked list.
 */

public class leetcode237 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
