package com.faithfulolaleru.LEETCODE;

import java.nio.charset.StandardCharsets;

public class LongestPalindromicSubstring {

    // 5. Longest Palindromic Substring (substring is continuous)

    public static void main(String[] args) {

        System.out.println(longestPalindromeSubString("racecar"));
        System.out.println(longestPalindromeSubString("abba"));
        System.out.println(longestPalindromeSubString("aaaabba"));

        System.out.println(longestPalindromeSubSequence("ELRMENMET"));
    }



    public static String longestPalindromeSubString(String s) {
        if(s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        // "racecar" "aabbaa"  // check for even length & odd length

        for (int i = 0; i < s.length(); i++) {
            int evenLength = expandFromMiddle(s, i, i);
            int oddLength = expandFromMiddle(s, i, i+1);
            int maxLength = Math.max(evenLength, oddLength);

            if(maxLength > end - start) {  // found a new longest palindrome, update start & end
                // we are at the palindrome center, so start would be half the maxlength to the left
                // end would be half the maxlength to the right

                start = i - ((maxLength - 1) / 2);
                end = i + (maxLength / 2);
            }
        }

        return s.substring(start, end + 1);
    }

    public static int expandFromMiddle(String s, int left, int right) {
        if(s == null || left > right) return 0;

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // start from the middle, if the char at the left = char at the right,
            // then they are palindromic, so keep expanding till they no longer same

            left--;
            right++;
        }

        return right - left - 1;
    }


    // Longest Palindromic Subsequence (subsequence doesnt have to be continuous just in place in order)
    // E.g "EMEME" is a valid Palindromic Subsequence for "ELRMENMET" even though its not continuous
    // solving using divide & conquer algorithm
    private static int longestPalindromeSubSequence(String string, int startIndex, int endIndex) {
        // pick first and last elements, if they match, it'll be 2 + f(2, n-1), i.e 2nd element and 2nd to the last
        // element; if they don't match, we can either pick 2nd element to compare with last element, or 1st element
        // with 2nd to last element i.e 0 + f(1, n-1) or 0 + (2, n); then get max of the 3 options


        // base conditions
        if(startIndex > endIndex) return 0; // we've reached & passed string middle

        if(startIndex == endIndex) return 1; // a palindrone with 1 character, it'll be the middle character

        int count1 = 0;

        // chars at start and end match
        if(string.charAt(startIndex) == string.charAt(endIndex)) {
            count1 = 2 + longestPalindromeSubSequence(string, startIndex + 1, endIndex - 1);
        }

        int count2 = longestPalindromeSubSequence(string, startIndex + 1, endIndex);
        int count3 = longestPalindromeSubSequence(string, startIndex, endIndex - 1);

        return Math.max(count1, Math.max(count2, count3));
    }

    public static int longestPalindromeSubSequence(String string) {
        return longestPalindromeSubSequence(string, 0, string.length() - 1);
    }
}
