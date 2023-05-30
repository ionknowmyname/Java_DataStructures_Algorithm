package com.faithfulolaleru.SearchAlgorithm;

import java.util.Arrays;

public class SearchAlgorithms {


    String name = "sassd";

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 10, 23, 11};
        SearchAlgorithms.linearSearch(arr, 10);
        SearchAlgorithms.binarySearchIterative(arr, 23);
    }

    public static int linearSearch(int arr[], int value) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value) {
                System.out.println("Element found at index: " + i);
                return i;
            }
        }
        System.out.println("Element " + value + " not found");
        return -1;
    }

    public static int binarySearchIterative(int arr[], int value) {

        Arrays.sort(arr);
        int start = 0, end = arr.length -1;
        int middle = (start + end)/2;
        // System.out.println(start + " " + middle + " " + end);

        while(arr[middle] != value  && start <= end) {   //  &&  to avoid infinite loop
            if(value < arr[middle]) {  // use first half
                end = middle - 1;
            } else {   // use 2nd half
                start = middle + 1;
            }
            middle = (start + end)/2;   // calculate new middle
            // System.out.println(start + " " + middle + " " + end);
        }


        if(arr[middle] == value){
            System.out.println("Element found at index: " + middle);
            return middle;
        } else {
            System.out.println("Element " + value + " not found");
            return -1;
        }
    }

    public static int binarySearchRecursive(int arr[], int value, int start, int end) {

        Arrays.sort(arr);
        int middle = (start + end)/2;


        if(start <= end) {   // to avoid infinite loop; can also use if
            if(arr[middle] == value) {
                System.out.println("Element found at index: " + middle);
                return middle;
            }

            if(value < arr[middle]) {  // use first half
                return binarySearchRecursive(arr, value, start, middle - 1);
            } else {   // use 2nd half
                return binarySearchRecursive(arr, value, middle + 1, end);
            }

        } else {
            System.out.println("Element " + value + " not found");
            return -1;
        }
    }
}
