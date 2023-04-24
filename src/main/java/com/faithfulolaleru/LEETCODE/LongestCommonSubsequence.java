package com.faithfulolaleru.LEETCODE;

public class LongestCommonSubsequence {

    /*
    *   subsequence don't have to be continuous, bt have to be in place i.e no changing order
    *   E.g ABCDE, ACE is a valid subsequence it don't have to be continuous like ABC
    *
    *       // https://www.youtube.com/watch?v=M_dpZ8IS_70
    *
    * */

    public static void main(String[] args) {

        System.out.println(longestCommonSubsequence("abcde", "ace"));  // ans should be 3
        System.out.println(longestCommonSubsequence("elephant", "erepat"));  // ans should be 5

        System.out.println(longestCommonSubsequence2("abcde", "ace"));  // ans should be 3
        System.out.println(longestCommonSubsequence2("elephant", "erepat"));  // ans should be 5

        System.out.println(longestCommonSubsequence3("abcde", "ace"));  // ans should be 3
        System.out.println(longestCommonSubsequence3("elephant", "erepat"));  // ans should be 5
    }


    // 1143. Longest Common Subsequence
    // Dynamic programming solution
    // memoization/recursive
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // initialize array to -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return longestCommonSubsequence(text1, text2, 0, 0, dp);
    }

    private static int longestCommonSubsequence(String text1, String text2, int i1, int i2, int[][] dp) {

        // base conditions
        if(i1 == text1.length() || i2 == text2.length()) return 0;

        if(dp[i1][i2] >= 0) {   // value has been memoized before don't recalculate
            return dp[i1][i2];
        }

        int c2, c3;

        // when chars match
        if(text1.charAt(i1) == text2.charAt(i2)) {
            dp[i1][i2] = 1 + longestCommonSubsequence(text1, text2, i1 + 1, i2 + 1, dp);
            // move to the next chars
        } else {
            // chars don't match, check if current char in text1 will match next char in text2
            c2 = longestCommonSubsequence(text1, text2, i1, i2 + 1, dp);

            // check if next char in text1 would match current char in text2
            c3 = longestCommonSubsequence(text1, text2, i1 + 1, i2, dp);

            dp[i1][i2] = Math.max(c2, c3);
        }

        return dp[i1][i2];
    }

    // dynamic programming solution 2
    // tabulation/iterative
    public static int longestCommonSubsequence3(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /*
                    // no need to initialize to 0, creating initializes to 0

                    if(i == 0 || j == 0) {
                        continue;  // coz array already initialized to all 0's
                    }
                */

                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // coz we added 1 more index to matrix so instead of 0 index, we start from 1, that's why we do -1
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }



    // Divide & Conquer solution
    // working bt time limit exceeded on leetcode
    private static int longestCommonSubsequence2(String text1, String text2, int i1, int i2) {

        // base conditions
        if(i1 == text1.length() || i2 == text2.length()) return 0;

        // when chars match
        int c1 = 0;
        if(text1.charAt(i1) == text2.charAt(i2)) {
            c1 = 1 + longestCommonSubsequence2(text1, text2, i1 + 1, i2 + 1);
            // move to the next chars
        }

        // chars don't match, check if current char in text1 will match next char in text2
        int c2 = longestCommonSubsequence2(text1, text2, i1, i2 + 1);

        // check if next char in text1 would match current char in text2
        int c3 = longestCommonSubsequence2(text1, text2, i1 + 1, i2);

        return Math.max(c1, Math.max(c2, c3));
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        return longestCommonSubsequence2(text1, text2, 0, 0);
    }
}
