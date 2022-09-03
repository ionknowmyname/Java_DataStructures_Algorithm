package com.faithfulolaleru.SortAlgorithm;

public class QuickSortAlgorithm {


    public static void main(String[] args) {
        int[] array = {5,7,4,16, 13, 12, 3};
        System.out.print("Before Sort --> ");
        QuickSortAlgorithm.printArray(array);
        System.out.println();
        QuickSortAlgorithm.quickSort(array, 0, array.length-1);
        System.out.print("After Sort --> ");
        QuickSortAlgorithm.printArray(array);
    }


    static int partition(int[] array, int start, int end) {
        int pivot = end;    // take the last element in array as pivot
        int i = start -1;

        for (int j = start; j <= end; j++) {
            if (array[j] <= array[pivot]) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        return i;
    }

    public static void quickSort(int[] array, int start, int end) {
        if(start < end) {
            int pivot  = partition(array, start, end);
            quickSort(array, start, pivot-1);    // for left part
            quickSort(array, pivot+1, end);   // right part
        }
    }

    public static void printArray(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
