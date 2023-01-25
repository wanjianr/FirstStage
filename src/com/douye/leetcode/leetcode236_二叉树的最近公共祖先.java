package com.douye.leetcode;

public class leetcode236_二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == p.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(p.left, p, q);
        TreeNode right = lowestCommonAncestor(p.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
