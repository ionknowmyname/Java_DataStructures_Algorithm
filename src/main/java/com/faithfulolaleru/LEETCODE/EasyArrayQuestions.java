package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);  // start with zero sum seen 1 time

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (preSum.containsKey(sum - k)) {
                // if there's a previous sum in the map that's = current sum - k,
                // it means k = current sum - that previous sum
                // and it also means that previous sum is equal in value to k
                // so we get how many occurences of that previous sum and add 1 to it to be our result


                result += preSum.get(sum - k);
            }

            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);  // update the num. of times we've seen the sum
        }

        return result;
    }

    // 53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxSum = Integer.MIN_VALUE; int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
            currentSum = Math.max(currentSum, 0);  // if currentSum is -ve, start new subarray

        }

        return maxSum;
    }g
}
