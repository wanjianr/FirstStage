package com.douye.leetcode;


import com.douye.queue.Dequeue;

import java.util.*;

public class leetcode103_二叉树的锯齿形层序遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            Deque<Integer> r = new LinkedList<>();
            int size = queue.size();
            TreeNode poll = queue.pollLast();
            for (int i = 0; i < size; i++) {
                if (flag) {
                    r.addLast(poll.val);
                } else {
                    r.addFirst(poll.val);
                }

                if(poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            flag = !flag;
            res.add(new ArrayList<>(r));
        }
        return res;
    }
}
