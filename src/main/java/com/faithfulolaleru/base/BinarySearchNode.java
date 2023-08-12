package com.faithfulolaleru.base;

public class BinarySearchNode {

    public int value;
    public BinarySearchNode left;
    public BinarySearchNode right;
    public int height;


    public BinarySearchNode() {
        this.height = 0;
    }

    public BinarySearchNode(int value) {
        this.value = value;
        this.height = 0;
    }
}
