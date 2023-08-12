package com.faithfulolaleru.HACKERRANK;

public class MinimumSwaps2 {

    /*
    *   https://www.hackerrank.com/challenges/minimum-swaps-2/problem
    *
    *   Minimum number of swaps to get array sorted ascending
    *
    *   [7, 1, 3, 2, 4, 5, 6]
    *
    *
    * */

    static int minimumSwaps(int[] arr) {

        int minSwaps = 0, index = 0, temp = 0;

        while(index < arr.length) {
            if(arr[index] != index + 1) {   // the number at index 0 should be 1, if it isnt, swap
                temp = arr[index];
                arr[index] = arr[arr[index] - 1];
                arr[temp - 1] = temp;

                minSwaps++;
            } else {
                index++;   // only go to the next index if the current index has its rightful value
            }
        }

        return minSwaps;
    }
}
