package com.faithfulolaleru.INTERVIEWS;

public class DigicoreLtd {

    // 66. Plus One  (LEETCODE)


    /*

        You are given a large integer represented as an integer array digits, where each digits[i]
        is the ith digit of the integer. The digits are ordered from most significant to least significant
        in left-to-right order. The large integer does not contain any leading 0's.

        Increment the large integer by one and return the resulting array of digits.

        Input: digits = [1,2,9] 1,2,9,9
        Output: [1,2,4]
        Explanation: The array represents the integer 123.
        Incrementing by one gives 123 + 1 = 124.
        Thus, the result should be [1,2,4].


        // solve in place with no new array


    */


    // my solution, kinda wrong
    public static int[] toSolve(int[] digits) {

        int l = digits.length;

        for(int i = l - 1; i >= 0; i--) {
            if(i == l - 1) digits[i] = digits[i] + 1;

            int c = i;

            while(digits[c] == 10) {
                digits[c] = 0;
                digits[c - 1] = digits[c - 1] + 1;
                c--;
            }

        }

        return digits;
    }

    // correct solution
    public static int[] toSolve2(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        // for when there's only [9], to become [1,0]
        digits = new int[digits.length + 1];
        digits[0] = 1;

        return digits;
    }
}
