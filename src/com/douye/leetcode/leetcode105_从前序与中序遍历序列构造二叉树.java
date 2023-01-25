package com.douye.leetcode;

import java.util.HashMap;
import java.util.Map;

public class leetcode105_从前序与中序遍历序列构造二叉树 {

    private Map<Integer, Integer> indexMap = new HashMap<>();

    /**
     * 从preorder中拿到根节点（第一个元素），通过map查询该根节点在inorder中的位置
     *  以此来确定左右子树在preorder的范围，然后迭代完成树的还原
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = inorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, length-1, 0, length-1);
    }

    public TreeNode dfs(int[] preorder, int[] inorder, int preLeft, int preRight, int midLeft, int mitRight) {
        if (preLeft > preRight) return null;
        int rootVal = preorder[preLeft];
        Integer midRootIndex = indexMap.get(rootVal);
        // 左子树中的节点个数
        int leftNum = midRootIndex - midLeft;
        TreeNode root = new TreeNode(rootVal);
        // 注意这里的坐标划分   左子树[preLeft + 1, preLeft + leftNum]   右子树 [preLeft + leftNum + 1, preRight]
        root.left = dfs(preorder, inorder, preLeft + 1, preLeft + leftNum, midLeft, midRootIndex - 1);
        root.right = dfs(preorder, inorder, preLeft + leftNum + 1, preRight, midRootIndex + 1, mitRight);
        return root;
    }
}
