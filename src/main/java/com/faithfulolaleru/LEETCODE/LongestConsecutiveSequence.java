package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class LongestConsecutiveSequence {



    // 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int index = 0; int tempCounter = 1; int maxCounter = 0;

        while(index < nums.length - 1) {
            if (nums[index] == nums[index + 1] - 1) {   // next number is 1 greater than current number
                // nums[index + 1] == nums[index] + 1
                tempCounter++;
                maxCounter = Math.max(maxCounter, tempCounter);
            } else if (nums[index] == (nums[index + 1])) {
                // don't increase counter, the current & next number are same, just proceed
                maxCounter = Math.max(maxCounter, tempCounter);
            } else {    // start counting all over
                tempCounter = 1;
                maxCounter = Math.max(maxCounter, tempCounter);
            }

            index++;
        }

        return maxCounter;
    }
}
