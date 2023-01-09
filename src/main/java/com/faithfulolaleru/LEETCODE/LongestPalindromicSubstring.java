package com.faithfulolaleru.LEETCODE;

import java.nio.charset.StandardCharsets;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("racecar"));
        System.out.println(longestPalindrome("abba"));
        System.out.println(longestPalindrome("aaaabba"));
    }



    public static String longestPalindrome(String s) {
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
}
