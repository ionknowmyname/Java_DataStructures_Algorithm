package com.faithfulolaleru.DivideAndConquer;

public class ConvertString {

    /*
    *   S1 and S2 are strings, convert S2 to S1 using delete, insert or replace operations
    *   find the minimum count of edit operations
    *   E.g carch to catch, replace r with t, so only 1 operation
    *   E.g 2; tbres to table = 3; insert a, r to l, remove s
    *
    * */


    public static void main(String[] args) {
        System.out.println(findMinOperations("table", "tbres"));
    }


    private static int findMinOperations(String s1, String s2, int i1, int i2) {
        // base conditions for recursive

        // if we reached the end of s1, insert remaining chars of s2 into s1
        if(i1 == s1.length()) return s2.length() - i2;  // this add remaining chars of s2 to s1

        // if we reached the end of s2, delete all remaining chars of s1
        if(i2 == s2.length()) return s1.length() - i1;

        // if both strings have matching chars, proceed to next chars
        if(s1.charAt(i1) == s2.charAt(i2)) {
            return findMinOperations(s1, s2, i1 + 1, i2 + 1);
        }

        int deleteOp = 1 + findMinOperations(s1, s2, i1 + 1, i2);
        int insertOp = 1 + findMinOperations(s1, s2, i1, i2 + 1);
        int replaceOp = 1 + findMinOperations(s1, s2, i1 + 1, i2 + 1);

        return Math.min(deleteOp, Math.min(insertOp, replaceOp));
    }

    public static int findMinOperations(String s1, String s2) {
        return findMinOperations(s1, s2, 0, 0);
    }
}
