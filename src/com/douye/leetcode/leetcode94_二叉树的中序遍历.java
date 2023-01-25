package com.douye.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode94_二叉树的中序遍历 {


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderByRecursion(root, res);
        return res;
    }


    /**
     * 前序遍历
     * @param root
     * @param res
     */
    public void previous(TreeNode root, List<Integer> res) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    /**
     * 二叉树中序遍历
     * @param root
     * @param res
     */
    public void inorderByRecursion(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorderByRecursion(root.left, res);
        res.add(root.val);
        inorderByRecursion(root.right, res);
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.isEmpty()) {
                temp = stack.pop();
            }
            res.add(temp.val);
            temp = temp.right;
        }
    }

    /**
     * 后序遍历
     * @param root
     * @param res
     */
    public void behind(TreeNode root, List<Integer> res) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode pop = stack1.pop();
            stack2.push(stack1.pop());
            if (pop.left != null) {
                stack1.push(pop.left);
            }
            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
    }
}
