package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.BinarySearchNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EasyBinaryTreeQuestions {



    // 102. Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(BinarySearchNode root) {
        List<List<Integer>> toReturn = new ArrayList<>();

        if(root == null) return toReturn;

        Queue<BinarySearchNode> queue = new LinkedList<>();
        // queue.add(root);
        queue.offer(root);   // better than add, only adds if not null .add would throw error if null

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            while(size-- > 0) {
                // BinarySearchNode presentNode = queue.remove();
                BinarySearchNode presentNode = queue.poll();
                // again .remove throws error if queue is empty, poll returns null if queue is empty

                currentLevel.add(presentNode.value);  // remove from queue & add its value to the list for that level
                if(presentNode.left != null) queue.offer(presentNode.left);
                if(presentNode.right != null) queue.offer(presentNode.right);
            }

            toReturn.add(currentLevel);  // add each level list to list to return
        }

        return toReturn;
    }

    // 110. Balanced Binary Tree
    public boolean isBalanced(BinarySearchNode root) {
        if(root == null) return true;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public boolean isBalanced2(BinarySearchNode root) {
        if(root == null) return true;

        return getHeight2(root) != -1;
    }

    public int getHeight(BinarySearchNode root) {
        if(root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getHeight2(BinarySearchNode root) {
        if(root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int diff = Math.abs(leftHeight - rightHeight);

        if(diff > 1 || leftHeight == -1 || rightHeight == -1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 104. Maximum Depth of Binary Tree
    public int maxDepth(BinarySearchNode root) {
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;  // root is level 1 not level 0

    }

    // 144. Binary Tree Preorder Traversal
    public List<Integer> preorderTraversal(BinarySearchNode root) {
        List<Integer> toReturn = new ArrayList<>();

        preOrder(root, toReturn);

        return toReturn;
    }

    public void preOrder(BinarySearchNode root, List<Integer> list) {
        if(root == null) return;

        list.add(root.value);

        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // 543. Diameter of Binary Tree
    public int diameterOfBinaryTree(BinarySearchNode root) {

        if(root == null) return 0;

        int diameter[] = new int[1];

        height(root, diameter);

        return diameter[0];
    }

    public int height(BinarySearchNode root, int diameter[]) {
        if(root == null) return 0;

        int left = height(root.left, diameter);
        int right = height(root.right, diameter);
        diameter[0] = Math.max(diameter[0], left + right);
        // take the result of each recursive call & set whichever is greater, the current diameter[0] or left + right
        // as new diameter[0]

        return 1 + Math.max(left, right);  // this just returns height of binary tree, we don't need it
    }

    // 572. Subtree of Another Tree
    public boolean isSubtree(BinarySearchNode root, BinarySearchNode subRoot) {
        if(root == null) return false;

        // only start to take into consideration if the two values match
        if(root.value == subRoot.value) {

            // check that they are identical i.e all their sub trees match, before returning that its a true subtree
            if(isIdentical(root, subRoot)) return true;
        }

        // if either the left branch or the right branch matches with the subroot, then its a sub tree
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isIdentical(BinarySearchNode node, BinarySearchNode subRoot) {

        if(node == null && subRoot == null) {
            return true;
        } else if(node == null || subRoot == null || node.value != subRoot.value) {
            return false;
        }

        // if subsequent right or left nodes don't match, then not identical
        if(!isIdentical(node.left, subRoot.left)) return false;
        if(!isIdentical(node.right,subRoot.right)) return false;

        return true;
    }

    public boolean isIdentical2(BinarySearchNode root, BinarySearchNode subRoot) {
        if(root == null && subRoot==null) return true;
        if(root == null || subRoot == null) return false;

        // only starts to do the recursive after they match, so if at any point they don't match,
        // it just throws false straight
        if(root.value == subRoot.value) {
            return isIdentical2(root.left, subRoot.left) && isIdentical2(root.right, subRoot.right);
        }

        return false;
    }

    // 100. Same Tree
    public boolean isSameTree(BinarySearchNode p, BinarySearchNode q) {
        if(p == null && q == null) {
            return true;
        } else if(p == null || q == null || p.value != q.value) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(BinarySearchNode p, BinarySearchNode q) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inOrder(p, list);
        inOrder(q, list2);

        if(list.equals(list2)) return true;
        else return false;
    }

    public void inOrder(BinarySearchNode p, List<Integer> list) {
        if(p == null) return;

        inOrder(p.left, list);

        list.add(p.value);

        inOrder(p.right, list);

        if(p.left == null) list.add(null);
        if(p.right == null) list.add(null);
    }
}

