package com.faithfulolaleru.DivideAndConquer;

public class NumberFactor {

    /*
    *   find number of ways to express N as a sum of a list of numbers
    *   E.g express 5 using (1,3,4);
    *   Ans is 6 ways, [ (1,1,1,1,1), (1,3,1), (3,1,1), (1,1,3), (4,1), (1,4) ]
    *
    * */


    public static void main(String[] args) {
        System.out.println(waysToGetN(4));  // should be 4
        System.out.println(waysToGetN(5));  // should be 6
        System.out.println(waysToGetN(6));  // should be 9

        System.out.println(waysToGetN3(4));  // should be 4

        System.out.println(waysToGetN4(6));  // should be 9
    }


    // divide & conquer
    public static int waysToGetN(int n) {

        // establish base cases for recursive
        if(n == 0 || n == 1 || n == 2) return 1;
        // only 1 way to get 0, only 1 way to get 2 also (1,1)

        if(n == 3) return 2;  // (1,1,1) & (3)

        int sub1 = waysToGetN(n - 1);
        int sub2 = waysToGetN(n - 3);
        int sub3 = waysToGetN(n - 4);
        // coz we using (1,3,4) as numbers to make up n

        return sub1 + sub2 + sub3;
    }

    // dynamic programming
    // memoization top down approach
    private static int waysToGetN2(int n, int[] dp) {

        // establish base cases for recursive
        if(n == 0 || n == 1 || n == 2) return 1;
        // only 1 way to get 0, only 1 way to get 2 also (1,1)

        if(n == 3) return 2;  // (1,1,1) & (3)

        // value already memoized
        if(dp[n] > 0) return dp[n];

        int sub1 = waysToGetN(n - 1);
        int sub2 = waysToGetN(n - 3);
        int sub3 = waysToGetN(n - 4);
        // coz we using (1,3,4) as numbers to make up n

        dp[n] = sub1 + sub2 + sub3;

        return dp[n];
    }

    // same as 2, just change if statements
    private static int waysToGetN3(int n, int[] dp) {

        // establish base cases for recursive
        if(n == 0 || n == 1 || n == 2) return 1;

        if(n == 3) return 2;  // (1,1,1) & (3)

        // value not memoized
        if(dp[n] == 0) {
            int sub1 = waysToGetN(n - 1);
            int sub2 = waysToGetN(n - 3);
            int sub3 = waysToGetN(n - 4);
            // coz we using (1,3,4) as numbers to make up n

            dp[n] = sub1 + sub2 + sub3;
        }

        return dp[n];
    }

    public static int waysToGetN3(int n) {
        int dp[] = new int[n + 1];

        return waysToGetN3(n, dp);
    }

    // dynamic progrmamming
    // tabulation bottom up approach
    public static int waysToGetN4(int n) {

        int dp[] = new int[n + 1];

        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i -3] + dp[i -  4];
        }

        return dp[n];
    }
}
