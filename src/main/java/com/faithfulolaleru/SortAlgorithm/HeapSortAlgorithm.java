package com.faithfulolaleru.SortAlgorithm;

import com.faithfulolaleru.BinaryTrees.BinaryHeap;

public class HeapSortAlgorithm {

    int[] arr = null;


    public HeapSortAlgorithm(int[] arr) {
        this.arr = arr;
    }


    public static void main(String[] args) {
        int arr[] = {2, 7, 3, 1, 4, 8, 9};
        HeapSortAlgorithm hs = new HeapSortAlgorithm(arr);
        hs.sort();
        hs.printArray();
    }



    public void sort() {
        BinaryHeap bh = new BinaryHeap(arr.length);

        // insert all array elements to Binary heap
        for(int i = 0; i < arr.length; i++) {
            bh.insert(arr[i], "Min");
        }

        // as you extract, save extracted value to the array
        for(int i = 0; i < arr.length; i++) {
            arr[i] = bh.extractHeadOfBH("Min");
        }
    }


    public void printArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
