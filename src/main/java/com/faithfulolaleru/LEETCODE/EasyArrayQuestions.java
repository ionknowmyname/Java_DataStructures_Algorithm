package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class EasyArrayQuestions {

    public static void main(String[] args) {

    }




    // 1480. Running Sum of 1d Array
    public int[] runningSum(int[] nums) {
        int[] toReturn = new int[nums.length];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            toReturn[i] = sum;
        }
        return toReturn;
    }

    // 724. Find Pivot Index
    // 1991. Find the Middle Index in Array
    public int pivotIndex(int[] nums) {  // middle index of array
        int totalSum = Arrays.stream(nums).reduce(0, Integer::sum);
        int leftSum = 0;

        for(int i = 0; i < nums.length; i++) {
            if(leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    // 560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum == k) {
                counter++;
            }
        }
        return counter;
    }
}
