package com.faithfulolaleru.INTERVIEWS.Turing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ElementOrderMapping {

    public static void main(String[] args) {
        int[] arr1 = {40, 10, 20, 30};
        int[] arr2 = {100, 100, 100};
        int[] arr3 = {37, 12, 28, 9, 100, 56, 80, 5, 12};

        System.out.println(Arrays.toString(mapElementOrder(arr1))); // Output: [4, 1, 2, 3]
        System.out.println(Arrays.toString(mapElementOrder(arr2))); // Output: [1, 1, 1]
        System.out.println(Arrays.toString(mapElementOrder(arr3))); // Output: [5, 3, 4, 2, 8, 6, 7, 1, 3]
    }

    public static int[] mapElementOrder(int[] arr) {
        // Step 1: Copy the array and sort the unique elements
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        // Step 2: Create a map to store the rank of each unique number
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : copy) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }

        // Step 3: Replace each element in the original array with its rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }
}
