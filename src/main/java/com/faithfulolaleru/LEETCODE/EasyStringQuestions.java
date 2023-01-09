package com.faithfulolaleru.LEETCODE;

public class EasyStringQuestions {

    public static void main(String[] args) {
        System.out.println(isPalindromeNumber(121));
        System.out.println(isPalindromeNumber(-121));
    }



    // 9. Palindrome Number
    public static boolean isPalindromeNumber(int x) {
        String s = String.valueOf(x);

        int i = 0;
        int j = s.length() - 1;

        while(i <= j) {
            if(s.charAt(i) != s.charAt(j)) return false;

            i++;
            j--;
        }

        return true;
    }
}
