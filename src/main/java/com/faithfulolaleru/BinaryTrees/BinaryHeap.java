package com.faithfulolaleru.BinaryTrees;

public class BinaryHeap {
    int arr[];
    int sizeOfTree;

    public BinaryHeap(int size){
        arr = new int[size + 1];     // add +1 coz we start filling into the array at 1 index not 0 index
        this.sizeOfTree = 0;
        System.out.println("Binary Heap has been created");
    }


    public static void main(String[] args) {
        BinaryHeap newBH = new BinaryHeap(5);
        newBH.insert(10, "Min");
        newBH.insert(5, "Min");
        newBH.insert(15, "Min");
        newBH.insert(1, "Min");
        newBH.levelOrderTraversal();
        // newBH.peek();
        newBH.extractHeadOfBH("Min");
        newBH.levelOrderTraversal();
    }




    public boolean isEmpty(){
        if(sizeOfTree == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer peek() {    // returns the root of binary heap
        if(isEmpty()) {
            System.out.println("Binary heap is empty");
            return null;
        }
        return arr[1];
    }

    // size of heap returns the number of filled cells in array, not array length
    public int sizeOfBH() {
        return sizeOfTree;
    }

    public void levelOrderTraversal(){
        for (int i = 1; i <= sizeOfTree; i++) {   // 1 coz 1st index is not used  // sizeOfTree not array size
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    // for inserting
    public void heapifyBottomToTop(int index, String heapType) {
        int parent = index / 2;    // coz when inserting child, left child index is 2x while right child is 2x+1

        if(index <= 1) { // we are at the root of BH
            return;
        }

        if(heapType == "Min") {   // parent node less than child node
            if(arr[index] < arr[parent]) {  // if current element is less than parent element, then swap
                int temp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = temp;
            }
        } else if (heapType == "Max") {
            if(arr[index] > arr[parent]) {  // if current element is greater than parent element, then swap
                int temp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = temp;
            }
        }

        heapifyBottomToTop(parent, heapType);
    }

    public void insert(int value, String heapType) {
        arr[sizeOfTree +1] = value;   // insert the value after the last element in array
        sizeOfTree++;
        heapifyBottomToTop(sizeOfTree, heapType);
        System.out.println("Successfully inserted " + value + " to BH");
    }

    // for extracting
    public void heapifyTopToBottom(int index, String heapType) {
        int leftChild = index * 2;
        int rightChild = index * 2 +1;
        int swapChild = 0;

        if(sizeOfTree <= leftChild) { // indexed value does not have a left child
            return;
        }

        if(heapType == "Max") {   // parent node greater than child node
            if(sizeOfTree == leftChild) {  // has only one child, which is left
                if(arr[index] < arr[leftChild]) {  // if current element is less than left child element, then swap
                    int temp = arr[index];
                    arr[index] = arr[leftChild];
                    arr[leftChild] = temp;
                }
                return;
            } else {  // if it has two children, take the greater child
                swapChild = arr[leftChild] > arr[rightChild] ? leftChild : rightChild;

                if(arr[index] < arr[swapChild]) {  // if current element is less than swap child element, then swap
                    int temp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = temp;
                }
            }
        } else if (heapType == "Min") {
            if(sizeOfTree == leftChild) {  // has only one child, which is left
                if(arr[index] > arr[leftChild]) {  // if current element is greater than left child element, then swap
                    int temp = arr[index];
                    arr[index] = arr[leftChild];
                    arr[leftChild] = temp;
                }
                return;
            } else {  // if it has two children, take the lesser child
                swapChild = arr[leftChild] < arr[rightChild] ? leftChild : rightChild;

                if(arr[index] > arr[swapChild]) {  // if current element is greater than swap child element, then swap
                    int temp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = temp;
                }
            }
        }

        heapifyTopToBottom(swapChild, heapType);
    }

    public int extractHeadOfBH(String heapType) {
        if(isEmpty()) return -1;

        int extractedValue = arr[1];  // coz its only the root node that'll be extracted
        arr[1] = arr[sizeOfTree];    // set the last element to replace the just removed root
        sizeOfTree--;

        heapifyTopToBottom(1, heapType);
        return extractedValue;
    }

    public void deleteBH(){
        arr = null;
        System.out.println("BH successfully deleted");
    }
}
