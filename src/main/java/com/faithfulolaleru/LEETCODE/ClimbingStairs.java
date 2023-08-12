package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class ClimbingStairs {





    // 70. Climbing Stairs
    // dynamic programming solution with memoization, no recursive
    public int climbStairsDP(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        int memo[] = new int[n + 1];

        memo[1] = 1;
        memo[2] = 2;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

    // recursive dynamic programming with memoization
    public static int climbStairsDPRecursive(int n) {
        int memo[] = new int[n + 1];
        Arrays.fill(memo,-1);

        return climbStairsDPRecursiveMain(n-1, memo) + climbStairsDPRecursiveMain(n-2, memo);
    }

    public static int climbStairsDPRecursiveMain(int n, int[] memo) {
        if(n < 0) return 0;
        if(n == 0 || n == 1) {
            memo[n] = 1;

            return memo[n];
        }

        if(memo[n] != -1) {  // if we've found fibonacci of a number, return it
            return memo[n];
        }

        int left = climbStairsDPRecursiveMain(n - 1, memo);
        int right = climbStairsDPRecursiveMain(n - 2, memo);
        memo[n] = left + right;

        return  memo[n];
    }
}
