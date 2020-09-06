package com.yyatsiuk.trees;

public class IsTreeSymmetric {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(3);
        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(1);
        TreeNode treeNode10 = new TreeNode(2);
        TreeNode treeNode11 = new TreeNode(2);
        TreeNode treeNode12 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(2);
        TreeNode treeNode14 = new TreeNode(1);
        TreeNode treeNode15 = new TreeNode(1);


        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        treeNode4.setLeft(treeNode8);
        treeNode4.setRight(treeNode9);
        treeNode5.setLeft(treeNode10);
        treeNode5.setRight(treeNode11);
        treeNode6.setLeft(treeNode12);
        treeNode6.setRight(treeNode13);
        treeNode7.setRight(treeNode14);
        treeNode7.setLeft(treeNode15);

        System.out.println("isSymmetric(treeNode1) = " + isSymmetric(treeNode1));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricHelper(root.getLeft(), root.getRight());
    }

    private static boolean isSymmetricHelper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        System.out.println(root1.getVal() + " " + root2.getVal());
        return root1.getVal().equals(root2.getVal())
                && isSymmetricHelper(root1.getLeft(), root2.getRight())
                && isSymmetricHelper(root1.getRight(), root2.getLeft());
    }

}
