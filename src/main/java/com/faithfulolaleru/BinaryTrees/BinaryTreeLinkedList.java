package com.faithfulolaleru.BinaryTrees;

import com.faithfulolaleru.base.BinaryNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLinkedList {

    BinaryNode root;

    public BinaryTreeLinkedList(){
        this.root = null;  // create an empty tree
    }


    public static void main(String[] args) {
        BinaryTreeLinkedList binaryTree = new BinaryTreeLinkedList();

        // add values to binary tree manually

        /*
        BinaryNode N1 = new BinaryNode();
        N1.value = "N1";
        BinaryNode N2 = new BinaryNode();
        N2.value = "N2";
        BinaryNode N3 = new BinaryNode();
        N3.value = "N3";
        BinaryNode N4 = new BinaryNode();
        N4.value = "N4";
        BinaryNode N5 = new BinaryNode();
        N5.value = "N5";
        BinaryNode N6 = new BinaryNode();
        N6.value = "N6";
        BinaryNode N7 = new BinaryNode();
        N7.value = "N7";
        BinaryNode N8 = new BinaryNode();
        N8.value = "N8";
        BinaryNode N9 = new BinaryNode();
        N9.value = "N9";

        N1.left = N2;
        N1.right = N3;
        N2.left = N4;
        N2.right = N5;
        N3.left = N6;
        N3.right = N7;
        N4.left = N8;
        N4.right = N9;
        binaryTree.root = N1;
        */

        // add values to binary tree with function

        binaryTree.insertValue("N1");
        binaryTree.insertValue("N2");
        binaryTree.insertValue("N3");
        binaryTree.insertValue("N4");
        binaryTree.insertValue("N5");
        binaryTree.insertValue("N6");
        binaryTree.insertValue("N7");
        binaryTree.insertValue("N8");
        binaryTree.insertValue("N9");


        binaryTree.preOrderTraversal(binaryTree.root);
        // binaryTree.inOrderTraversal(binaryTree.root);
        // binaryTree.postOrderTraversal(binaryTree.root);
        // binaryTree.levelOrderTraversal();
        // binaryTree.searchForValue("N5");
        // System.out.println(binaryTree.getDeepestNode().value);

    }





    void preOrderTraversal(BinaryNode node){
        // root -> left -> right

        if(node == null){  // we have reached the leaf node, so stop recursive call
            return;
        }

        System.out.print(node.value + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    void inOrderTraversal(BinaryNode node){
        // left -> root -> right

        if(node == null){  // we have reached the leaf node, so stop recursive call
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.value + " ");
        inOrderTraversal(node.right);
    }

    void postOrderTraversal(BinaryNode node){
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

        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            // remove root node, check if left and right have values, add left then right,
            // on next iteration of while loop, the left node of former iteration is now the one that's removed
            // while it is removed, now check if it has child nodes on the left and right then add them, left first
            // on next iteration, the right node of first root iteration is now the first in queue and is removed
            // while its child left and right nodes are added if any

            // this way you are adding child left & right nodes to queue before the parent node is removed and printed



            BinaryNode presentNode = queue.remove();  // remove first node in queue and store in presentNode
            System.out.print(presentNode.value + " ");

            if(presentNode.left != null){
                queue.add(presentNode.left);
            }
            if(presentNode.right != null){
                queue.add(presentNode.right);
            }
        }
    }

    public void searchForValue(String value) {
        // use levelOrder traversal

        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);

        while(!queue.isEmpty()){

            BinaryNode presentNode = queue.remove();
            if(presentNode.value == value){
                System.out.println("The value - " + value + " is found in tree");
                return;
            } else {
                if(presentNode.left != null){
                    queue.add(presentNode.left);
                }
                if(presentNode.right != null){
                    queue.add(presentNode.right);
                }
            }
        }

        System.out.println("The value - " + value + " is not found in tree");
    }

    public void insertValue(String value){
        // use levelOrder traversal

        BinaryNode newNode = new BinaryNode(value);

        if(root == null){
            root = newNode;
            System.out.println("Successfully inserted new node at Root");
            return;
        }

        // Else search for vacant node level by level then add new node

        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();

            if(presentNode.left == null) {     // left is vacant
                presentNode.left = newNode;
                System.out.println("Successfully inserted at left node");
                break;
            } else if (presentNode.right == null) {    // right is vacant
                presentNode.right = newNode;
                System.out.println("Successfully inserted at right node");
                break;
            } else {     // none is vacant, continue traversing to find vacant
                queue.add(presentNode.left);
                queue.add(presentNode.right);
            }
        }
    }

    public void deleteNode(String value){
        // find node to delete, look for the deepest node, set its value to the node you wanna delete, and delete deepest node

        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();

            if(presentNode.value == value){  // found node
                // set deepest node value to the value of node you wanna delete
                presentNode.value = getDeepestNode().value;

                // now delete deepest node
                deleteDeepestNode();
                System.out.println("Node successfully deleted");
                return;
            } else {  // continue traversing the tree and adding to queue
                if(presentNode.left != null){
                    queue.add(presentNode.left);
                }
                if(presentNode.right != null){
                    queue.add(presentNode.right);
                }
            }
        }
        System.out.println("Node does not exist in Binary Tree");
    }

    public BinaryNode getDeepestNode(){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        BinaryNode presentNode = null;
        while(!queue.isEmpty()){
            presentNode = queue.remove();

            if(presentNode.left != null){
                queue.add(presentNode.left);
            }
            if(presentNode.right != null){
                queue.add(presentNode.right);
            }
        }

        return presentNode;
    }

    public void deleteDeepestNode(){
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        BinaryNode previousNode, presentNode = null;
        while(!queue.isEmpty()){
            previousNode = presentNode;
            presentNode = queue.remove();

            if(presentNode.left == null){
                // if presentNode left child is null, it means previous node right child was the last node, so set
                // previous node right child to null to delete it

               previousNode.right = null;
               return;
            } else if(presentNode.right == null){
                // if presentNode right child is null, it means presentNode left child was the last node, so set
                // presentNode left child to null to delete it

                presentNode.left = null;
                return;
            }

            queue.add(presentNode.left);
            queue.add(presentNode.right);
        }


    }

    public void deleteCompleteBinaryTree(){
        root = null;
        System.out.println("Binary Tree Successfully Deleted");
    }

}
