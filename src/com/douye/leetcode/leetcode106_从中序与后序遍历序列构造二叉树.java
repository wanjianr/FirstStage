package com.douye.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leetcode106_从中序与后序遍历序列构造二叉树 {
    private Map<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(inorder, postorder, 0, length-1, 0, length-1);
    }

    public TreeNode dfs(int[] inorder, int[] postorder, int midLeft, int mitRight, int postLeft, int postRight) {
        if (postLeft > postRight) return null;
        int rootVal = postorder[postRight];
        Integer midRootIndex = indexMap.get(rootVal);
        // 左子树中的节点个数
        int leftNum = midRootIndex - midLeft;
        TreeNode root = new TreeNode(rootVal);
        // 注意这里的坐标划分   左子树[midLeft + 1, midLeft + leftNum - 1]   右子树 [preLeft + leftNum, postRight - 1]
        root.left = dfs(inorder, postorder, midLeft, midRootIndex - 1, postLeft,postLeft + leftNum - 1);
        root.right = dfs(inorder, postorder, midRootIndex + 1, mitRight, postLeft + leftNum, postRight - 1 );
        return root;
    }
}
