package com.faithfulolaleru.SortAlgorithm;

public class SortAlgorithms {


    public static void main(String[] args) {

        SortAlgorithms sort = new SortAlgorithms();
        int arr1[] = {5, 30, 25, 6, 40};

        sort.bubbleSort(arr1);
        sort.printSort(arr1);
        sort.selectionSort(arr1);
        sort.printSort(arr1);
        sort.insertionSort(arr1);
        sort.printSort(arr1);
    }

    void bubbleSort(int arr[]){
        int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    void selectionSort(int[] arr){
        for (int j = 0; j < arr.length; j++) {
            int minimumIndex = j;

            for (int i = j+1; i < arr.length; i++) {
                if(arr[i] < arr[minimumIndex]){
                    minimumIndex = i;
                }
            }

            if(minimumIndex != j){
                int temp = arr[j];
                arr[j] = arr[minimumIndex];
                arr[minimumIndex] = temp;
            }
        }
    }

    void insertionSort(int arr[]){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j = i;
            while(j < 0 && arr[j-1] > temp){     // (arr[j-1] > temp) means previous element greater than current element in iteration
                arr[j] = arr[j-1];   // swap positions i.e take the prev element that's greater and set to the current element that's smaller
                j--;
            }
            arr[j] = temp;
        }
    }



    void printSort(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
