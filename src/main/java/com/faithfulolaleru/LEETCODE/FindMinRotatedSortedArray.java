package com.faithfulolaleru.LEETCODE;

public class FindMinRotatedSortedArray {



    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        // find smallest number i.e start of array
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) { // smaller values on the right, use right subarray
                left = middle + 1;
            } else {  // smaller values on the left, use left subarray
                right = middle;
            }
        }

        // after loop, int left would be the smallest array value
        int start = left;

        return nums[start];
    }

    public int findMin2(int[] nums) {
        int start = 0, end = nums.length - 1, ans = Integer.MAX_VALUE;

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(nums[start] <= nums[mid]){
                ans = Math.min(ans, nums[start]);
                start = mid + 1;
            }
            else{
                ans = Math.min(ans, nums[end]);
                end = end - 1;
            }
        }
        return ans;
    }
}
