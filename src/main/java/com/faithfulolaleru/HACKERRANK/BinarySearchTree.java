package com.faithfulolaleru.HACKERRANK;

import com.faithfulolaleru.base.BinarySearchNode;

import java.util.Queue;

public class BinarySearchTree {

    static BinarySearchNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinarySearchTree newBST = new BinarySearchTree();

        // newBST.insert(70);
        newBST.root = new BinarySearchNode(60);
        newBST.root.left = new BinarySearchNode(40);
        newBST.root.right = new BinarySearchNode(70);
        newBST.root.left.left = new BinarySearchNode(20);
        newBST.root.left.right = new BinarySearchNode(50);
        newBST.root.right.left = new BinarySearchNode(65);
        newBST.root.right.right = new BinarySearchNode(75);


        System.out.println(newBST.findLowestCommonAncestor(root, 70, 75).value);
    }


    // given two nodes values, find the closest ancestor (i.e node that has both nodes as descendants) to the leaves
    public BinarySearchNode findLowestCommonAncestor(BinarySearchNode root, int value1, int value2) {

        /*

        Queue<>
        BinarySearchNode rootCopy = root;
        BinarySearchNode rootCopy2 = root;


        // find value1 node's path
        while(rootCopy != null) {
            if(value1 == rootCopy.value) {  // found
                break;
            } else if (value1 < rootCopy.value) {

            }
        }

        // find value2 node's path

        */

        if (root == null) {
            return null;
        }
        if (value1 == value2) {
            return null;
        }
        if (value1 == root.value || value2 == root.value) {
            return root;
        }
        if ((value1 < root.value && value2 > root.value)
                || (value2 < root.value && value1 > root.value)) {

            return root;
        }

        // if both less than or if both greater than
        if (value1 < root.value && value2 < root.value) {
            return findLowestCommonAncestor(root.left, value1, value2);
        } else {
            return findLowestCommonAncestor(root.right, value1, value2);
        }
    }


    // fails some tests on leetcode, works fine on hackerrank?
    public BinarySearchNode findLowestCommonAncestor2(BinarySearchNode root, BinarySearchNode node1, BinarySearchNode node2) {

        if (root == null) {
            return null;
        }
        if (root == node1 || root == node2) {
            return root;
        }
        if ((root.value > node1.value && root.value < node2.value)
                || (root.value < node1.value && root.value > node2.value)) {

            return root;
        }
        BinarySearchNode left = findLowestCommonAncestor2(root.left, node1, node2);
        return left == null ? findLowestCommonAncestor2(root.right, node1, node2) : left;
    }

    // leetcode solution
    public BinarySearchNode findLowestCommonAncestor3(BinarySearchNode root, BinarySearchNode p, BinarySearchNode q) {

        if(root == null || root.value == p.value || root.value == q.value) return root;

        BinarySearchNode left = findLowestCommonAncestor3(root.left,p,q);
        BinarySearchNode right = findLowestCommonAncestor3(root.right,p,q);

        if(left == null) return right;
        if(right == null) return left;

        return root;
    }

}
