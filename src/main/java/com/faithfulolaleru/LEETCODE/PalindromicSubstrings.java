package com.faithfulolaleru.LEETCODE;

public class PalindromicSubstrings {

    public static void main(String[] args) {
        // countSubstrings4 & countSubstrings2 are best solutions
    }


    // 647. Palindromic Substrings
    // Brute force
    public int countSubstrings(String s) {
        int count = 0;
        String str = "";

        for(int i = 0; i < s.length(); i++) {
            str="";

            for(int j = i; j < s.length(); j++) {
                Character ch = s.charAt(j);
                str += ch;

                if(isPalindrome(str)) count++;
            }
        }

        return count;
    }

    public boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;

        while(i <= j) {
            char left = str.charAt(i);
            char right = str.charAt(j);
            if(left != right) return false;

            i++;
            j--;
        }

        return true;
    }

    // brute force 2  // left & right pointer set dynamically
    public int countSubstrings2(String s) {
        int totalCount = 0;

        for(int i = 0; i < s.length(); i++) {
            int count = 1;   // each char already accounts for 1, being a palindrome in itself

            for(int j = i + 1; j < s.length(); j++) { // start from next char after i, for each i
                if(isPalindrome2(s, i, j)) count++;
            }

            totalCount += count;
        }

        return totalCount;
    }

    public boolean isPalindrome2(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) return false;

            left++;
            right--;
        }
        return true;
    }

    // don't full understand this, but its the simplest solution
    // DP bottom up tabulation
    public int countSubstrings3(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        for (int g = 0; g < n; g++) {
            for (int i = 0, j = g; j < n; i++, j++) {
                if (g == 0) dp[i][j] = true;
                else if (g == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }

                if (dp[i][j]) count++;
            }
        }

        return count;
    }

    // final, best, and easiest solution
    public int countSubstrings4(String s) {

        for (int i = 0; i < s.length(); i++) {
           count(s, i, i);  // for odd length strings
           count(s, i, i + 1);  // for even length strings
        }

        return result;

        /*
        *   EXPLANATION: start at the first character, count leftwards and rightwards, for every character that matches,
        *   that's one more palindrome to count for result. You then move to the next character and count leftwards
        *   and rightwards again, till you get to last character
        * */
    }

    int result = 0;

    private void count(String s, int start, int end) {
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            result++;
            start--;
            end++;
        }
    }
}
