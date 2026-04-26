package com.faithfulolaleru.SortAlgorithm;

import java.util.Random;

public class MergeSortAlgorithm {


    public static void main(String[] args) {
        int[] sizes = {10, 50, 100, 200};
        Random rand = new Random();

        // int[] array = {5,7,4,16, 13, 12, 3};

        for (int size : sizes) {
            int[] arr1 = new int[size];
            for (int i = 0; i < size; i++) {
                arr1[i] = rand.nextInt(10000);
            }

            long start1 = System.nanoTime();
            mergeSort(arr1, 0, arr1.length - 1);
            long mergeTime = System.nanoTime() - start1;

            System.out.printf("%-10d %-20d%n", size, mergeTime);
        }


        // MergeSortAlgorithm.mergeSort(array, 0, array.length-1);   // can call the methods directly without instantiating object of class coz of static
        // MergeSortAlgorithm.printArray(array);
    }




    public static void printArray(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        // System.out.println();
    }

    public static void merge(int[] A, int left, int middle, int right){
        int[] leftTempArray = new int[middle-left+2];   // +2 to avoid index out of range exception
        int[] rightTempArray = new int[right-middle+1];   // +1 to avoid index out of range exception

        for (int i = 0; i <= middle-left; i++) {
            leftTempArray[i] = A[left + i];   // copy the left part of A array to the left Temp array
        }
        for (int i = 0; i < right-middle; i++) {
            rightTempArray[i] = A[middle + 1 + i];   // copy the right part of A array to the right Temp array
        }

        leftTempArray[middle-left+1] = Integer.MAX_VALUE;
        rightTempArray[right-middle] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = left; k <= right; k++) {
            if(leftTempArray[i] < rightTempArray[j]){
                A[k] = leftTempArray[i];
                i++;
            } else {
                A[k] = rightTempArray[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right){
        if(right > left){
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);    // for left part
            mergeSort(arr, middle+1, right);      // for the right part
            merge(arr, left, middle, right);
        }
    }
}
