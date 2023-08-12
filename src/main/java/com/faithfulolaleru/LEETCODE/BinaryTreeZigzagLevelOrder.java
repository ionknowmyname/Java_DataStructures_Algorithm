package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.BinarySearchNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrder {


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
                    currentLevel.add(0, presentNode.value); // put at beginning each time,
                    // the former 1st element should shift to the right by 1 each time
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

    public List<List<Integer>> zigzagLevelOrder2(BinarySearchNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);

        return sol;
    }

    private void travel(BinarySearchNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;

        if(sol.size() <= level) {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }

        List<Integer> collection = sol.get(level);
        if(level % 2 == 0) collection.add(curr.value);
        else collection.add(0, curr.value);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
