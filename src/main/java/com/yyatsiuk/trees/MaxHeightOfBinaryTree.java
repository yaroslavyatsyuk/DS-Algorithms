package com.yyatsiuk.trees;

public class MaxHeightOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
    }
}

