package com.faithfulolaleru.DynamicProgramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridTraveller {

    /*
    *   How many ways can you travel from the top left to bottom right in a m x n grid
    *
    *   exact solution for (62. Unique Paths) problem in Leetcode using DP as well
    * */


    public static void main(String[] args) {
//        System.out.println(pureRecursionSolution(1, 1));
//        System.out.println(pureRecursionSolution(2, 3));
//        System.out.println(pureRecursionSolution(3, 2));
//        System.out.println(pureRecursionSolution(3, 3));
        // System.out.println(pureRecursionSolution(18, 18));  // would take too long

//        System.out.println();
//        System.out.println(dynamicProgrammingSolution(1, 1, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(2, 3, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(3, 2, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(3, 3, new HashMap<>()));
        System.out.println(dynamicProgrammingSolution(18, 18, new HashMap<>()));  // still taking too long
    }


    // pure recursion
    public static int pureRecursionSolution(int m, int n) {
        if(m == 1 && n == 1) return 1;  // only 1 way to travel a 1x1 gird
        if(m == 0 || n == 0) return 0;   // no grid if there's a 0

        return pureRecursionSolution(m - 1, n) + pureRecursionSolution(m, n - 1);

        // coz once you go either right(n-1) or down(m-1), the available options decrease by 1
        // reducing m & n simultaneously would mean you moving diagonally which ain't allowed according to the problem
    }

    // memoization
    // my impl is faulty
    public static int dynamicProgrammingSolution(int m, int n, Map<int[], Integer> map) {

        int[] mnArray = {m, n};
        // int[] mnArray2 = {n, m};  // coz 2x3 = 3x2

        if(map.containsKey(mnArray)) return map.get(mnArray);  // solution for m & n already in map, use it
        // || map.containsKey(mnArray2)

        if(m == 1 && n == 1) return 1;  // only 1 way to travel a 1x1 gird
        if(m == 0 || n == 0) return 0;   // no grid if there's a 0

        // not in map yet, add it before continuing
        int ans = dynamicProgrammingSolution(m - 1, n, map) + dynamicProgrammingSolution(m, n - 1, map);
        map.put(mnArray, ans);
        // map.put(mnArray2, ans);

        return map.get(mnArray);
    }

    // correct impl & working
    public static int dynamicProgrammingSolution2(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                    // only 1 way of going from grid to grid for border row & column (1st row & 1st column)
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    // add results of the two ways to get to the current location; either you moved right or
                    // moved down to get to current location
                }
            }
        }

        // return what is memoized for the last row - last column
        return dp[m - 1][n - 1];
    }

    public static int dynamicProgrammingSolution3(int m, int n) {

        int[][] dp = new int[m][n];  // initializes with all 0 values

        // find path from (0,0) to (m, n)
        return findPath(0, 0, m, n, dp);
    }

    public static int findPath(int path1, int path2, int m, int n, int[][] dp) {
        if (path1 + 1 == m && path2 + 1 == n) return 1;  // I don't get this line
        if (path1 == m) return 0; // starting point = ending point, not moving anywhere
        if (path2 == n) return 0;  // same here

        if (dp[path1][path2] != 0) return dp[path1][path2];  // found result, return saved value

        dp[path1][path2] = findPath(path1 + 1, path2, m, n, dp) + findPath(path1, path2 + 1, m ,n, dp);

        return dp[path1][path2];
    }
}

