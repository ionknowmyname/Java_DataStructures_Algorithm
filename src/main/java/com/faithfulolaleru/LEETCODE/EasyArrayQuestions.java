package com.faithfulolaleru.LEETCODE;

import java.util.*;

public class EasyArrayQuestions {

    public static void main(String[] args) {

        System.out.println(removeDuplicates(new int[] { 1, 1, 2 }));
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
    }

    // 26. Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] nums) {

        if(nums.length == 0) return 0;

        int addIndex = 1; // index that unique nums will be inserted at

        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] < nums[i + 1]) {
                // if current num less than next num, then next num is not a duplicate or not the same as current
                nums[addIndex] = nums[i + 1];
                addIndex++;
            }

            // so if current & next num are same, addIndex won't increase bt i would increase
        }

        return addIndex;
    }

    // 169. Majority Element
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();

        int maxCount = 0, maxCountInt = 0;
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);

            if(count.get(nums[i]) > maxCount) {
                maxCount = count.get(nums[i]);
                maxCountInt = nums[i];
            }
        }

        return maxCountInt;
    }

    // 27. Remove Element
    // my solution, worked on first try
    public int removeElement(int[] nums, int val) {

        if(nums.length == 0) return 0;

        int trackerIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[trackerIndex] = nums[i];
                trackerIndex++;
            }
        }


        return trackerIndex;
    }

    public boolean containsDuplicate(int[] nums) {
        /*Set newSet = new HashSet();
        newSet.addAll(Arrays.asList(nums));

        return newSet.size() != nums.length;*/

        return Arrays.stream(nums).distinct().count() < nums.length;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> newSet = new HashSet<>();

        for (int num : nums) {
            if (newSet.contains(num)) return true;
        }

        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                return true;
            }
        }

        return false;
    }
}
