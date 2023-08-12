package com.faithfulolaleru.LEETCODE;

public class ProductArraySelf {

    /*
    *   run two loops, forward and backward, forward takes product of all preceding
    *   numbers and store in array of i, then backwards take product of all numbers
    *   after i and store in array of i. Finally multiply both numbers in array of i
    *   for both forward & backward
    *
    * */


    // 238. Product of Array Except Self
    // https://www.youtube.com/watch?v=tSRFtR3pv74   // tutorial
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;

        int[] leftProducts = new int[N];
        int[] rightProducts = new int[N];
        int[] totalProducts = new int[N];

        leftProducts[0] = 1; // the 1st nums number multiplies 1, so product array[0] is 1
        rightProducts[N - 1] = 1; // the last nums number multiplies 1, so product array[N-1] is 1

        for (int i = 1; i < N; i++) {
            leftProducts[i] = nums[i - 1] * leftProducts[i - 1];
            // current product is the previous product X previous number in array
        }

        for (int i = N-2; i >= 0; i--) {
            rightProducts[i] = nums[i + 1] * rightProducts[i + 1];
            // current product is the product for the number in front of current X number in front of current number in array
        }

        for (int i = 0; i < N; i++) {  // traverse full input and multiply left & right
            totalProducts[i] = leftProducts[i] * rightProducts[i];
        }

        return totalProducts;
    }

    public int[] productExceptSelf2 (int[] nums) {

        // solution to make space complexity O(1) by getting rid of extra left & right arrays that aren't really needed
        int N = nums.length;

        int[] totalProducts = new int[N];

        totalProducts[0] = 1; // the 1st nums number multiplies 1, so product array[0] is 1

        for (int i = 1; i < N; i++) {
            totalProducts[i] = nums[i - 1] * totalProducts[i - 1];
            // current product is the previous product X previous number in array
        }

        int R = 1;  // variable to hold all the products from the right up to the current element
        for (int i = N-1; i >= 0; i--) {
            // start from last element this time (N-1) since you not setting 1st value in right array to 1
            totalProducts[i] = R * totalProducts[i];
            // totalProducts[i] is now total products from left side, so multiply by product from right side
            // which is R

            R = R * nums[i];
            // total product from right side starts from 1, then multiplied by current number, so R is already
            // set for the next iteration
        }

        return totalProducts;
    }
}
