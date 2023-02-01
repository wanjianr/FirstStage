package com.douye.leetcode;

public class leetcode142_环形链表II {
    public ListNode detectCycle(ListNode head) {
        // 注：f = a + nb，f = 2s  ->  s = nb 另外 遍历时经过入口节点的步数为 a + nb
        ListNode slow = head;
        ListNode quick = head;
        // quick != slow 不能作为while条件，因为初始状态下quick == slow
        while (true) {
            if (quick == null || quick.next == null) return null;
            slow = slow.next;
            quick = quick.next.next;
            if(quick == slow) break;
        }
        quick = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }
}
