package com.faithfulolaleru.LEETCODE;

public class MaximumProductSubarray {

    /*
    *  subarray is continuous; for [-2,0,-1], [-2,-1] is not a subarray
    *
    *   https://leetcode.com/problems/maximum-product-subarray/solutions/1608862/java-3-solutions-detailed-explanation-using-image/
    * */

    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int currMin = 1;   // tracking currMin coz of -ve nums that can multiply themselves to give a + num
        int currMax = 1;
        int res = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            res = Math.max(res, nums[i]);  // set res to max value in nums array
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                // if there's 0, in between the array nums, reset currMax & currMin
                // this also restarts the subarray, coz each subsequent num multiplying currMax & currMin
                // would now be that number, effectively starting the multiplication again

                currMax = 1;
                currMin = 1;
                continue;
            }

            // int temp = currMax * nums[i];
            int temp = currMax;
            // store max down coz before you update currMin, currMax would already have been updated

            // set currMax to the max of (currMax * nums[i]), (currMin * nums[i]) & curr num
            currMax = Math.max(Math.max(currMax * nums[i], currMin * nums[i]), nums[i]);

            // set currMin to the min of temp, (currMin * nums[i]) & curr num
            currMin = Math.min(Math.min(temp * nums[i], currMin * nums[i]), nums[i]);

            // on multiplying with a -ve num, currMin thats already -ve becomes +ve i.e currMax; while currMax that's
            // +ve becomes -ve becoming currMin; so each time we get a -ve num, currMax & currMin switch

            res = Math.max(currMax, res);
        }

        return res;
    }

    // two pointer solution
    public int maxProduct2(int[] nums) {
        int maxProduct = nums[0], leftMax = 0, rightMax = 0;

        for (int i = 0; i < nums.length; i++) {
            // this does the same restarting the subarray as the previous solution
            // so at the beginning, set leftMax & rightMax to 1 since they are 0,
            // if you encounter a 0 in nums, i.e num[i] = 0, leftMax would be 0
            // & get set to 1 to restart the subarray

            leftMax = (leftMax == 0 ? 1 : leftMax) * nums[i];
            rightMax = (rightMax == 0 ? 1 : rightMax) * nums[nums.length - 1 - i];
            maxProduct = Math.max(maxProduct, Math.max(leftMax, rightMax));
        }

        return maxProduct;
    }
}
