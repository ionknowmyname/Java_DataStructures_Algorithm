package com.faithfulolaleru.DynamicProgramming;

public class LongestIncreasingSubsequence {

    // Leetcode
    // 300. Longest Increasing Subsequence

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = 0;

        // initialize all values in dp to 1; meaning at the beginning, the longest increasing subsequence
        // at each point is 1
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // just make sure before you update, the current value at that index is less than the
                // new computation, else don't update
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {  // && dp[i] < dp[j] + 1
                    dp[i] = dp[j] + 1;
                    // dp[i] = Math.max(dp[i], dp[j + 1]);
                }
            }
        }

        // pick the max value in dp
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
