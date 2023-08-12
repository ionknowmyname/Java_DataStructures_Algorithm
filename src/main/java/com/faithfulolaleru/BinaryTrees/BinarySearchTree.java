package com.faithfulolaleru.BinaryTrees;

import com.faithfulolaleru.base.BinarySearchNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    BinarySearchNode root;

    public BinarySearchTree(){
        this.root = null;
        // // create an empty search tree
    }



    public static void main(String[] args) {
        BinarySearchTree newBST = new BinarySearchTree();
        newBST.insert(70);
        newBST.insert(50);
        newBST.insert(90);
        newBST.insert(30);
        newBST.insert(60);
        newBST.insert(80);
        newBST.insert(100);
        newBST.insert(20);
        newBST.insert(40);

        // newBST.preOrderTraversal(newBST.root);
        // newBST.searchForValue(newBST.root, 50);
        //newBST.deleteNode(newBST.root, 200);
        newBST.delete(200);
        newBST.levelOrderTraversal();
    }





    public BinarySearchNode insertNode(BinarySearchNode currentNode, int value){
        if(currentNode == null){
            BinarySearchNode newNode = new BinarySearchNode(value);
            System.out.println("Successfully inserted new node with value -> " + value);
            return newNode;
        } else if (value <= currentNode.value) {
            currentNode.left = insertNode(currentNode.left, value);
            return currentNode;
        } else {
            currentNode.right = insertNode(currentNode.right, value);
            return currentNode;
        }
    }

    public void insert(int value){
        root = insertNode(root, value);  // this method not inserting well, the root is still always null, check it out later
    }

    public void preOrderTraversal(BinarySearchNode node){
        // root -> left -> right

        if(node == null){  // we have reached the leaf node, so stop recursive call
            return;
        }

        System.out.print(node.value + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal(BinarySearchNode node){
        // left -> root -> right

        if(node == null){  // we have reached the leaf node, so stop recursive call
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.value + " ");
        inOrderTraversal(node.right);
    }

    void postOrderTraversal(BinarySearchNode node){
        // left -> right -> root

        if(node == null){  // we have reached the leaf node, so stop recursive call
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value + " ");
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

    BinarySearchNode searchForValue(BinarySearchNode node, int value){
        if (node == null) {
            System.out.println("Value: " + value + " not found in BST");
            return null;
        } else if (node.value == value) {
            System.out.println("Value: " + value + " found in BST");
            return node;
        } else if (value < node.value) {
            return searchForValue(node.left, value);
        } else {
            return searchForValue(node.right, value);
        }
    }

    /*public static BinarySearchNode findSuccessorNode(BinarySearchNode root){
        // Successor node is minimum node in right subtree; we need to find it in case we want to
        // delete a node that has 2 children, this successor node would replace the root node to be deleted


    }*/

    public BinarySearchNode deleteNode(BinarySearchNode node, int value) {
        if(node == null) {
            System.out.println("Value not found in BST");
            return null;   // nothing deleted
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

        return node;
    }

    public void delete(int value) {
        root = deleteNode(root, value);
    }

    public static BinarySearchNode findMinimumNode(BinarySearchNode root){
        if(root.left == null){
            return root;
        } else {
            return findMinimumNode(root.left);
        }
    }

    public void deleteEntireBST() {
        root = null;
        System.out.println("Entire BST deleted successfully");
    }


}
