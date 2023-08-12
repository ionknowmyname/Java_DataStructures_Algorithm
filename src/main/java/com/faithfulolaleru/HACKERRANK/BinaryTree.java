package com.faithfulolaleru.HACKERRANK;

import com.faithfulolaleru.base.BinaryNode;

public class BinaryTree {

    public BinaryNode invertBinaryTree(BinaryNode root) {

        if (root == null) {
            return null;
        }

        BinaryNode right = invertBinaryTree(root.right);
        BinaryNode left = invertBinaryTree(root.left);
        root.left = right;
        root.right = left;

        return root;
    }
}
