package com.faithfulolaleru.HACKERRANK;

public class PalindromeIndex {

    public static int palindromeIndex(String s) {

        if (s == null || s.length() < 2) return -1;

        int l = 0, r = s.length() - 1;

        if (isPalindrome(s, l, r)) return -1;
        while (l < r) {
            if (isPalindrome(s, l+1, r)) return l;
            if (isPalindrome(s, l, r-1)) return r;
            if (s.charAt(l) != s.charAt(r)) return -1;
            l++;
            r--;
        }

        return -1;
    }

    public static int palindromeIndex2(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end && s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
        }
        if (start >= end) return -1; // already a palindrome
        if (isPalindrome(s, start + 1, end)) return start;
        if (isPalindrome(s, start, end - 1)) return end;

        return -1;
    }

    public static int palindromeIndex3(String str) {
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return isPalindrome(str, i + 1, j) ? i : j;
            }
        }
        return -1;
    }

    public static boolean isPalindrome(String s, int l, int r) {

        if (s == null || l > r) return false;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }

        return true;
    }

    public static boolean isPalindrome2(String s, int l, int r) {

        while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }

        return l >= r;
    }

    static boolean isPalindrome3(String str, int beginIndex, int endIndex) {
        for (int i = beginIndex, j = endIndex; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
