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
}
