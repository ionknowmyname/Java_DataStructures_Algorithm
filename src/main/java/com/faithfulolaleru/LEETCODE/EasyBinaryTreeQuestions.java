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

    // 103. Binary Tree Zigzag Level Order Traversal
    public List<List<Integer>> zigzagLevelOrder(BinarySearchNode root) {

        List<List<Integer>> toReturn = new ArrayList<>();
        boolean positive = true;

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

                if(positive) {
                    currentLevel.add(presentNode.value);  // remove from queue & add its value to the list for that level
                } else {
                    currentLevel.add(0, presentNode.value);
                }

                if(presentNode.left != null) queue.offer(presentNode.left);
                if(presentNode.right != null) queue.offer(presentNode.right);

            }
            // positive = true ? false : true;
            positive = !positive;

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
}
