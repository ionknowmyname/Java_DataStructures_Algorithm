package com.faithfulolaleru.BinaryTrees;

public class BinaryTreeArray {

    String[] arr;
    int lastUsedIndex;


    public BinaryTreeArray(int size){
        arr = new String[size +1];  // coz index 0 won't be used
        this.lastUsedIndex = 0;
        System.out.println("Blank tree of size " + size + " has been created");
    }


    public static void main(String[] args) {
        BinaryTreeArray newBinaryTree = new BinaryTreeArray(9);
        newBinaryTree.insertValue("N1");
        newBinaryTree.insertValue("N2");
        newBinaryTree.insertValue("N3");
        newBinaryTree.insertValue("N4");
        newBinaryTree.insertValue("N5");
        newBinaryTree.insertValue("N6");
        newBinaryTree.insertValue("N7");
        newBinaryTree.insertValue("N8");
        newBinaryTree.insertValue("N9");

        System.out.println();
        newBinaryTree.preOrderTraversal(1);

        System.out.println();
        newBinaryTree.inOrderTraversal(1);

        System.out.println();
        newBinaryTree.postOrderTraversal(1);

        System.out.println();
        newBinaryTree.levelOrderTraversal();
    }



    boolean isFull(){
        if(arr.length -1 == lastUsedIndex){
            return true;
        } else {
            return  false;
        }
    }

    void insertValue(String value){
        if(!isFull()){
            arr[lastUsedIndex +1] = value;  // insert at the index after lastUsedIndex
            lastUsedIndex++;
            System.out.println("Successfully inserted: " + value);
        } else {
            System.out.println("BinaryTree is full");
        }
    }

    public void preOrderTraversal(int index){  // at first, index is 1 for root
        // root -> left -> right

        if(index > lastUsedIndex){ // no elements in cells with index greater than index of lastUsedIndex
            return;
        }

        System.out.print(arr[index] + " ");
        preOrderTraversal(index * 2);   // for left tree
        preOrderTraversal(index * 2 +1);     // for right tree
    }

    public void inOrderTraversal(int index){  // at first, index is 1 for root
        // left -> root -> right

        if(index > lastUsedIndex){
            return;
        }

        inOrderTraversal(index * 2);   // for left tree
        System.out.print(arr[index] + " ");
        inOrderTraversal(index * 2 +1);     // for right tree
    }

    public void postOrderTraversal(int index){  // at first, index is 1 for root
        // left -> right -> root

        if(index > lastUsedIndex){
            return;
        }

        postOrderTraversal(index * 2);   // for left tree
        postOrderTraversal(index * 2 +1);     // for right tree
        System.out.print(arr[index] + " ");
    }

    public void levelOrderTraversal(){
        // just traverse the array regularly would visit elements level by level

        for (int i = 1; i <= lastUsedIndex; i++) { // coz root starts from 1 index, 0 index is empty
            System.out.print(arr[i] + " ");
        }
    }

    public int searchForValue(String value){
        // use levelOrder traversal, therefore just looping the array

        for (int i = 1; i <= lastUsedIndex; i++) { // coz root starts from 1 index, 0 index is empty
            if(arr[i] == value){
                System.out.println(value + " exists at index: " + i);
                return i;    // return index for where we found the value
            }
        }
        System.out.println("Value not in Binary Tree");
        return  -1;
    }

    public void delete(String value){
        // find node to delete, look for the deepest node, set its value to the node you wanna delete, and delete deepest node

        int location = searchForValue(value);   // location of value to delete

        if(location == -1){  // value not in the binary tree
            return;
        } else {
            arr[location] = arr[lastUsedIndex];  // set the value at the last index to the index of the value you wanna delete
            lastUsedIndex--;     // deletes the last value
            System.out.println("Node successfully deleted");
        }
    }

    public void deleteCompleteBinaryTree(){
        try {
            arr = null;
            System.out.println("Binary Tree Successfully Deleted");
        } catch (Exception e) {
            System.out.println("Error deleting Binary Tree");
        }
    }
}
