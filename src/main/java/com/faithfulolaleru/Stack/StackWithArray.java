package com.faithfulolaleru;

public class StackWithArray {

    int[] arr;  // int arr[]; also correct
    int topOfStack;


    public StackWithArray(int size){
        this.arr = new int[size];
        this.topOfStack = -1; // empty stack
        System.out.println("The Stack is created with size: " + size);
    }



    public static void main(String[] args) {
        StackWithArray newStack = new StackWithArray(4);
        System.out.println(newStack.isEmpty());
        System.out.println(newStack.isFull());

    }


    public boolean isEmpty(){
        /*if(topOfStack == -1) {
            return true;
        }else {
            return false;
        }*/


        boolean value = (topOfStack == -1) ? true : false;

        return value;
    }

    public boolean isFull(){
        if(topOfStack == arr.length -1){
            // System.out.println("Stack is full");
            return  true;
        }else{
            return false;
        }
    }

    public void push(int value){
        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            arr[topOfStack + 1] = value;
            topOfStack++;
            System.out.println("Successfully added to Stack");
        }
    }

    public int pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            int remove = arr[topOfStack];
            topOfStack--;
            System.out.println("Successfully removed from Stack");

            return remove;
        }
    }

    public int peek(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return arr[topOfStack];
        }
    }

    public void deleteStack(){
        arr = null;
        System.out.println("Successfully deleted stack");
    }
}
