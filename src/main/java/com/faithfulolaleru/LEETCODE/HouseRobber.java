package com.faithfulolaleru.LEETCODE;

public class HouseRobber {


    public static void main(String[] args) {
        int[] houses = { 6, 7, 1, 30, 8, 2, 4 };  // new int[]
        System.out.println(rob(houses));  // should be 41
        System.out.println(rob2(houses));  // should be 41
        System.out.println(rob3(houses));  // should be 41
        System.out.println(rob4(houses));  // should be 41
    }



    // 198. House Robber
    // using dynamic programming
    // bottom up tabulation
    public static int rob(int[] nums) {
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

    // bottom up tabulation 2
    // mine, working
    public static int rob3(int[] nums) {
        if(nums.length == 0) return 0;

        int memo[] = new int[nums.length + 2];
//        memo[0] = 0;
//        memo[1] = nums[0];

        for (int i = nums.length - 1; i >= 0; i--) {
            memo[i] = Math.max(memo[i + 1], memo[i + 2] + nums[i]);
        }

        return memo[0];
    }


    // divide & conquer approach
    private static int maxMoneyDaC(int[] houseNetWorth, int currentIndex) {
        // base conditions
        if(currentIndex >= houseNetWorth.length) return 0;
        // our current house has overshot available houses

        int stealCurrentHouse = houseNetWorth[currentIndex] + maxMoneyDaC(houseNetWorth, currentIndex + 2);
        int skipCurrentHouse = maxMoneyDaC(houseNetWorth, currentIndex + 1);

        return Math.max(stealCurrentHouse, skipCurrentHouse);
    }

    public static int rob2(int[] houseNetWorth) {
        return maxMoneyDaC(houseNetWorth, 0);
    }


    // using dynamic programming 2
    // top down memoization approach
    // working
    private static int maxMoneyDP(int[] houseNetWorth, int currentIndex, int[] dp) {
        // base conditions
        if(currentIndex >= houseNetWorth.length) return 0;
        // our current house has overshot available houses

        // calc not yet memoized, then memoize
        if(dp[currentIndex] == 0) {
            int stealCurrentHouse = houseNetWorth[currentIndex] + maxMoneyDP(houseNetWorth, currentIndex + 2, dp);
            int skipCurrentHouse = maxMoneyDP(houseNetWorth, currentIndex + 1, dp);

            dp[currentIndex] = Math.max(stealCurrentHouse, skipCurrentHouse);
        }

        return dp[currentIndex];
    }

    public static int rob4(int[] houseNetWorth) {

        int[] dp = new int[houseNetWorth.length + 1];

        return maxMoneyDP(houseNetWorth, 0, dp);
    }
}
