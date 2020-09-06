package com.yyatsiuk.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 */
public class BinaryTreeLevelOrderTraversal {

    private static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int currentLevelElemCount = 1;
        int nextLevelElemCount = 0;

        List<List<Integer>> resultingList = new ArrayList<>();
        ArrayList<Integer> level = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            level.add(node.getVal());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
                nextLevelElemCount++;
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
                nextLevelElemCount++;
            }

            currentLevelElemCount--;
            if (currentLevelElemCount == 0) {
                if (!level.isEmpty()) {
                    resultingList.add(level);
                }
                level = new ArrayList<>();
                currentLevelElemCount = nextLevelElemCount;
                nextLevelElemCount = 0;
            }
        }

        return resultingList;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1, null, null);
        TreeNode n2 = new TreeNode(2, n1, null);
        TreeNode n4 = new TreeNode(4, null, null);
        TreeNode n6 = new TreeNode(6, null, null);
        TreeNode n5 = new TreeNode(5, n4, n6);
        TreeNode n3 = new TreeNode(3, n2, n5);
        TreeNode n10 = new TreeNode(10, null, null);
        TreeNode n11 = new TreeNode(11, n10, null);
        TreeNode n9 = new TreeNode(9, null, n11);
        TreeNode n8 = new TreeNode(8, null, n9);
        TreeNode n7 = new TreeNode(7, n3, n8);

        System.out.println(levelOrder(n7));
    }
}
