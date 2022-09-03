package com.faithfulolaleru.BinaryTrees;

import com.faithfulolaleru.base.BinarySearchNode;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    BinarySearchNode root;

    AVLTree(){
        root = null;
    }



    public static void main(String[] args) {

        AVLTree newAvL= new AVLTree();
        newAvL.insert(5);
        newAvL.insert(10);
        newAvL.insert(15);
        newAvL.insert(20);
        newAvL.levelOrderTraversal();
        newAvL.delete(5);
        System.out.println();
        newAvL.levelOrderTraversal();
    }




    void levelOrderTraversal(){
        // level 1 -> level N, from left to right    // level 1 = root node, level N = last leaf level

        Queue<BinarySearchNode> queue = new LinkedList<BinarySearchNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            // remove root node, check if left and right have values, add left then right,
            // on next iteration of while loop, the left node of former iteration is now the one that's removed
            // while it is removed, now check if it has child nodes on the left and right then add them, left first
            // on next iteration, the right node of first root iteration is now the first in queue and is removed
            // while its child left and right nodes are added if any

            // this way you are adding child left & right nodes to queue before the parent node is removed and printed



            BinarySearchNode presentNode = queue.remove();  // remove first node in queue and store in presentNode
            System.out.print(presentNode.value + " ");

            if(presentNode.left != null){
                queue.add(presentNode.left);
            }
            if(presentNode.right != null){
                queue.add(presentNode.right);
            }
        }
    }

    public int getHeight(BinarySearchNode node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private BinarySearchNode rotateRight(BinarySearchNode disbalancedNode){
        BinarySearchNode newRoot = disbalancedNode.left;
        disbalancedNode.left = disbalancedNode.left.right;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        // add 1 for the disbalanced node itself, coz
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private BinarySearchNode rotateLeft(BinarySearchNode disbalancedNode){
        BinarySearchNode newRoot = disbalancedNode.right;
        disbalancedNode.right = disbalancedNode.right.left;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        // add 1 for the disbalanced node itself, coz
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    public int getBalance(BinarySearchNode node){     // check to see if node is balanced
        if(node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private BinarySearchNode insertNode(BinarySearchNode node, int nodeValue) {
        if (node == null) {
            BinarySearchNode newNode = new BinarySearchNode(nodeValue);
            newNode.height = 1;
            return newNode;
        } else if (nodeValue < node.value) {
           node.left = insertNode(node.left, nodeValue);
        } else {
            node.right = insertNode(node.right, nodeValue);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        // Now balance tree with rotations
        if(balance > 1 && nodeValue < node.left.value) {  // then its left-left condition, do right rotation
            return rotateRight(node);
        }
        if(balance > 1 && nodeValue > node.left.value) {  // then its left-right condition, do left then right rotation
            node.left = rotateLeft(node.left);   // left rotate on disbalanced node left child so its left left
            return rotateRight(node);   //   right rotate on grandparent node
        }
        if(balance < -1 && nodeValue > node.right.value) {  // then its right-right condition, do left rotation
            return rotateLeft(node);
        }
        if(balance < -1 && nodeValue < node.right.value) {  // then its right-left condition, do right then left rotation
            node.right = rotateRight(node.right);   // right rotate on disbalanced node right child so its right right
            return rotateLeft(node);   //   left rotate on grandparent node
        }

        return node;
    }

    public void insert(int value) {
        // public method to access/use private insertNode method, this is for if we are inserting from a diff. class
        // bt since I'm inserting from this class, I can use the insertNode method directly

        root = insertNode(root, value);
    }

    public static BinarySearchNode findMinimumNode(BinarySearchNode root){
        if(root.left == null){
            return root;
        } else {
            return findMinimumNode(root.left);
        }
    }

    public BinarySearchNode deleteNode(BinarySearchNode node, int value) {
        if(node == null) {
            System.out.println("Value not found in BST");
            return node;   // node is null, so can also return null
        }

        if (value < node.value){
            node.left = deleteNode(node.left, value);
        } else if (value < node.value) {
            node.right = deleteNode(node.right, value);
        } else {   // the current node is the node we want to delete
            if (node.left != null && node.right != null) {    // node to delete has two children
                BinarySearchNode temp  = node;
                BinarySearchNode minNodeForRight = findMinimumNode(temp.right);   // min node in right subtree is the successor node
                node.value = minNodeForRight.value;  // replace the node to delete with the successor node's value
                node.right = deleteNode(node.right, minNodeForRight.value);    // delete the successor node
            } else if (node.left != null) {   // node to delete has left child
                node = node.left;    // set the left child as new root, thereby deleting root
            } else if (node.right != null) {    // node to delete has right child
                node = node.right;    // set the right child as new root
            } else {     // node to delete has no child i.e leaf node
                node = null;
            }
        }


        // Next check if rotation is needed after deleting
        int balance = getBalance(node);
        int balanceLeft = getBalance(node.left);
        int balanceRight = getBalance(node.right);

        if(balance > 1 && balanceLeft >= 0){   // left-left
            return rotateRight(node);
        }
        if(balance > 1 && balanceLeft < 0){   // left-right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if(balance < -1 && balanceRight <= 0){   // right-right
            return rotateLeft(node);
        }
        if(balance < -1 && balanceRight > 0){   // right-left
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }


        return node;
    }

    public void delete(int value) {
        root = deleteNode(root, value);
    }
}
