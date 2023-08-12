package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.BinarySearchNode;

public class BinaryTreeMaxPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(BinarySearchNode root) {
        helper(root);
        return max;
    }

    // helper returns the max branch
    // plus current node's value
    int helper(BinarySearchNode root) {
        if (root == null) return 0;

        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        max = Math.max(max, root.value + left + right);

        return root.value + Math.max(left, right);
    }

    public int maxPathSum2(BinarySearchNode root) {
        int []maxValue = new int[1];

        maxValue[0] = Integer.MIN_VALUE;
        maxPathDown(root, maxValue);

        return maxValue[0];

    }

    public static int maxPathDown(BinarySearchNode root, int []maxValue){
        if(root == null)
            return 0;

        int lh = Math.max(0, maxPathDown(root.left, maxValue));
        int rh = Math.max(0, maxPathDown(root.right, maxValue));

        maxValue[0] = Math.max(maxValue[0], root.value + lh + rh);

        return root.value + Math.max(lh, rh);
    }
}
