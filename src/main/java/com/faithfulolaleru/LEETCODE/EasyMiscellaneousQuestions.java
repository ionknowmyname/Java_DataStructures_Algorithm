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
}
