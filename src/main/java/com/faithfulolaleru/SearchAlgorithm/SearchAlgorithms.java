package com.faithfulolaleru.SearchAlgorithm;

public class SearchAlgorithms {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 10, 23, 11};
        SearchAlgorithms.linearSearch(arr, 10);
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
}
