package com.faithfulolaleru.INTERVIEWS.Turing;

import java.util.*;
import java.util.stream.Collectors;

public class MaxTwoSum {

    /*
     *   return max two sum less than a target i.e the s
     *   when you find the two numbers, return their indexes in the list
     */


    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 7, 3};
        List<Integer> arr2 = List.of(1, 5, 7, 32, 8, 1, 4, 5);

        Integer maxSum = MaxTwoSum.withSorting(arr2, 10);
        System.out.println(maxSum);
    }


    public static Integer withSorting(List<Integer> nums, int target) {
        List<Integer> sorted = nums.stream().sorted().collect(Collectors.toList());

        // Arrays.sort(int[]); for if its an int array

        int maxSum = sorted.get(0);

        for (int i = 0; i < sorted.size()-1; i++) {
            // -1 coz of out of bound index, right index shooting out of list

            int rightIndex = i + 1;
            int currentSum = sorted.get(i) + sorted.get(rightIndex);

            if(currentSum < target) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    // not tested
    public Integer withoutSorting(List<Integer> nums, int target) { // int[]

        int left = 0; int maxSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < nums.size(); right++) {
            if(nums.get(left) + nums.get(right) < target) {
                maxSum = Math.max(maxSum, nums.get(left) + nums.get(right));
            }
            left++;
        }

        return maxSum;
    }
}
