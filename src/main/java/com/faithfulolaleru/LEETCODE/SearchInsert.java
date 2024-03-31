package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class SearchInsert {

    // 35. Search Insert Position


    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6} , 7));
    }

    // my original code, worked on first attempt
    public static int searchInsert(int[] nums, int target) {

        Arrays.sort(nums);
        int start = 0, end = nums.length -1;

        if(nums[end] < target) return end + 1;   // nums[end + 1];   // mimic inserting target at end
        if(nums[start] > target) return 0;   // nums[0];   // mimic inserting target at beginning

        int middle =  start + (end - start) / 2;  // (start + end)/2;
        // System.out.println(start + " " + middle + " " + end);

        while(start <= end) {   //  &&  to avoid infinite loop
            if(nums[middle] == target) {
                return middle;
            }

            if(target < nums[middle]) {  // use first half
                end = middle - 1;
            } else {   // use 2nd half
                start = middle + 1;
            }

            // calculate new middle
            middle = start + (end - start) / 2;  // (start + end)/2;
        }

        return start;  // nums[start + 1];
    }
}
