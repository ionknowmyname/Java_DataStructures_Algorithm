package com.faithfulolaleru.LEETCODE;

public class EasyMiscellaneousQuestions {


    // 278. First Bad Version
    // use binary search
    // time limit exceeded for some tests
    public int firstBadVersion(int n) {
        int low = 0, high = n, middle = (low + high) / 2;

        while(low <= high) {
            // int mid = low + (high-low)/2;
            if(isBadVersion(middle) == true && isBadVersion(middle - 1) == false) return middle;
            else if(isBadVersion(middle) == false) { // first bad version in upper half
                low = middle + 1;
                middle = (low + high) / 2;  // calc new middle
            } else {  // first bad version in lower half
                high = middle;
                middle = (low + high) / 2;  // calc new middle
            }
        }

        return -1;
    }

    public int firstBadVersion2(int n) {
        int low = 0, high = n;

        while(low <= high) {
             int middle = low + (high - low)/2;
            if(isBadVersion(middle) == true && isBadVersion(middle - 1) == false) return middle;
            else if(isBadVersion(middle) == false) { // first bad version in upper half
                low = middle + 1;
            } else {  // first bad version in lower half
                high = middle;
            }
        }

        return -1;
    }

    private Boolean isBadVersion(int middle) {
        return null;
    }

    // 191. Number of 1 Bits
    public int hammingWeight(int n) {
        int counter = 0;
        while(n != 0) {
            n = n & (n - 1);
            // & = bitwise AND operation, both left & right side have to be 1 for result to be 1, other wise, result is 0

            counter++;
        }

        return counter;
    }

    public int hammingWeight2(int n) {
        boolean isNeg = false;

        if(n < 0) {
            isNeg = true;
            n = ~n;
            // ~ = unary bitwise complement operator, it inverts the bits of an integer value,
            // changing 0 bits to 1 and 1 bits to 0.
            // E.g a = 5; binary representation = 0000 0000 0000 0000 0000 0000 0000 0101
            // int b = ~a;
            // System.out.println(b); // output: -6 (binary representation = 1111 1111 1111 1111 1111 1111 1111 1010)
        }

        int count = 0;
        while(n > 0) {
            n = n & (n - 1);
            count++;
        }

        return isNeg ? 32 - count : count;
    }

    // 67. Add Binary
    public String addBinary(String a, String b) {

        // Initialize two pointers to traverse the binary strings from right to left
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder ans = new StringBuilder();
        int carry = 0;

        // Loop until both pointers have reached the beginning of their respective strings & there is no carry-over value left
        while (i >= 0 || j >= 0 || carry != 0) {
            // Add the current binary digit in string a, if the pointer is still within bounds
            if (i >= 0) {
                carry += a.charAt(i) - '0';  // subtract by '0' to convert the numbers from a char type into an int
                i--;
            }

            // Add the current binary digit in string b, if the pointer is still within bounds
            if (j >= 0) {
                carry += b.charAt(j) - '0';  // subtract by '0' to convert the numbers from a char type into an int
                j--;
            }

            // Calculate the next binary digit in the result by taking the remainder of the sum divided by 2
            ans.append(carry % 2);

            // this is the exact value it carries
            carry = carry / 2;

            // e.g 1 + 1 = 2, 2 % 2 = 0, so it appends 0, 2 / 2 = 1, it now carries 1 as carry to the next loop
        }

        // Reverse the result and return it as a string
        return ans.reverse().toString();
    }
}
