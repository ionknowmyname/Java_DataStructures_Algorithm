package com.faithfulolaleru.LEETCODE;

public class MinCostClimbingStairs {

    // 746. Min Cost Climbing Stairs
    // Greedy Algorithm   // passes all tests
    public int minCostClimbingStairs(int[] cost) {

        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }

        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

    // dynammic programming   // top down memoization
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];

        return Math.min(minCost(cost, n - 1, dp), minCost(cost, n - 2, dp));
    }

    private int minCost(int[] cost, int n, int[] dp) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return cost[n];

        if (dp[n] != 0) return dp[n];  // already previously calculated, just return

        dp[n] = cost[n] + Math.min(minCost(cost, n - 1, dp), minCost(cost, n - 2, dp));

        return dp[n];
    }

    // dynammic programming   // bottom up tabulation
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (i < 2) dp[i] = cost[i];   // either first or 2nd stair,
            else dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
        // return minimum between last & 2nd to last, since you can climb 2 steps at a go
    }

    // Bottom up computation   // not DP
    public int minCostClimbingStairs4(int[] cost) {
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];

        if (n <= 2) return Math.min(first, second);

        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(first, second);
            first = second;  // first moves forward
            second = current;  // second moves forward to current
        }

        return Math.min(first, second);  // first & second are now 2nd to last & last, respectively
    }
}
