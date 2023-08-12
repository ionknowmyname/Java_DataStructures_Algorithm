package com.faithfulolaleru.HACKERRANK;

public class ThreeInOneStackQuestion {

    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public ThreeInOneStackQuestion(int stackSize){
       stackCapacity = stackSize;
       values = new int[stackSize * numberOfStacks];
       sizes = new int[numberOfStacks];
    }


    public static void main(String[] args) {
        ThreeInOneStackQuestion newStack = new ThreeInOneStackQuestion(3);
    }



    public boolean isFull(int stackNum){
        if(sizes[stackNum] == stackCapacity){
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(int stackNum){
        if(sizes[stackNum] == 0){
            return true;
        } else {
            return false;
        }
    }

    private int indexOfTop(int stackNum){   // e.g for first stack, stackNum = 0
        int offset = stackNum * stackCapacity; // therefore offset = 0
        // this offset is added if we are in the 2nd/3rd stack, e.g if 2nd stack, stackNum would be 1, offset would be 3

        int size = sizes[stackNum]; // if only 2 of the 3 have values, size would be 2
        return offset + size -1;  // 1 would be returned, 0 for 1st value, 1 for 2nd value
    }

    public void push(int stackNum, int value){
        if(isFull(stackNum)){
            System.out.println("Stack is full");
        } else{
            sizes[stackNum]++;  // increase size of that particular stack, so indexOfTop is now +1
            values[indexOfTop(stackNum)] = value;
        }
    }

    public int pop(int stackNum){
        if(isEmpty(stackNum)){
            System.out.println("Stack is empty");
            return -1;
        } else {
            int topIndex = indexOfTop(stackNum);
            int topValue = values[topIndex]; // get the value at that top index
            values[topIndex] = 0;  // then set that value to 0 as placeholder for it being deleted/popped
            sizes[stackNum]--;
            return topValue;
        }
    }

    public int peek(int stackNum){
        if(isEmpty(stackNum)){
            System.out.println("Stack is empty");
            return -1;
        } else {
            return values[indexOfTop(stackNum)];
        }
    }
}
