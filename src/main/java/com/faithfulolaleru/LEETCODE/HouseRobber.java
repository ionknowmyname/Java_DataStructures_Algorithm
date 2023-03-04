package com.faithfulolaleru.LEETCODE;

public class HouseRobber {


    // 198. House Robber
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;

        int memo[] = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];  // max money at first is the one in 1st house

        for (int i = 1; i < nums.length; i++) {

            memo[i + 1] = Math.max(memo[i], memo[i - 1] + nums[i]);
            // next max is the max between current max & (former max + current house)
        }

        return memo[nums.length];
     }
}
