package com.douye.leetcode;

import java.util.*;

public class leetcode199_二叉树的右视图 {
    /**
     * 这里需要使用队列
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.poll();
                if (pop.left != null) stack.offer(pop.left);
                if (pop.right != null) stack.offer(pop.right);
                if (i == size - 1) {
                    res.add(pop.val);
                }
            }
        }
        return res;
    }

    /**
     * 深度有限搜索
     * @param root
     * @return
     */
    private List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView1(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    public void dfs(TreeNode node, int deep) {
        if (node == null) return;
        if (deep == result.size()) {
            result.add(node.val);
        }
        dfs(node.right, deep+1);
        dfs(node.left, deep+1);
    }

}
