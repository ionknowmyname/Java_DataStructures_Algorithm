package com.faithfulolaleru.LEETCODE;

public class HouseRobber {


    public static void main(String[] args) {
        int[] houses = { 6, 7, 1, 30, 8, 2, 4 };  // new int[]
        System.out.println(rob(houses));  // should be 41
        System.out.println(rob2(houses));  // should be 41
    }



    // 198. House Robber
    // using dynamic programming
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


    // divide & conquer approach
    private static int maxMoneyRecursive(int[] houseNetWorth, int currentIndex) {
        // base conditions
        if(currentIndex >= houseNetWorth.length) return 0;
        // our current house has overshot available houses

        int stealCurrentHouse = houseNetWorth[currentIndex] + maxMoneyRecursive(houseNetWorth, currentIndex + 2);
        int skipCurrentHouse = maxMoneyRecursive(houseNetWorth, currentIndex + 1);

        return Math.max(stealCurrentHouse, skipCurrentHouse);
    }

    public static int rob2(int[] houseNetWorth) {
        return maxMoneyRecursive(houseNetWorth, 0);
    }
}
