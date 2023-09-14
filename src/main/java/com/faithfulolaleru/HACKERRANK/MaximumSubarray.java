package com.faithfulolaleru.HACKERRANK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumSubarray {


//    https://www.hackerrank.com/challenges/maxsubarray/problem?isFullScreen=true


    public static List<Integer> maxSubarray(List<Integer> arr) {

        List<Integer> results = new ArrayList<>();
        int subArraySum = 0;
        int sumSubSequence = 0;
        int subArraySumMax = 0;

        for (int i = 0; i < arr.size(); i++) {

            // this is to get the max subarray sum
            int current = arr.get(i);
            subArraySum += current;

            if(subArraySum < 0) subArraySum = 0;

            subArraySumMax = Math.max(subArraySumMax, subArraySum);

            // this is to get the max subsequence sum

            if (current > 0) sumSubSequence += current;
        }

        if (sumSubSequence > 0) {
            results.add(subArraySumMax);
            results.add(sumSubSequence);
        } else {
            //there are no positive values so we pick the highest value
            final int max = Collections.max(arr);
            results.add(max);
            results.add(max);
        }

        return results;
    }

    public static List<Integer> maxSubarray2(List<Integer> arr) {
        int subarrayMax = subArray(arr);
        int subsequenceMax = subSequence(arr);

        return List.of(subarrayMax, subsequenceMax);
    }

    static private int subArray(List<Integer> arr) {
        int subArraySum = arr.get(0);
        int subArraySumMax = arr.get(0);

        for(int i = 1; i < arr.size(); i++)  {
            subArraySum = Math.max(arr.get(i), subArraySum + arr.get(i));
            subArraySumMax = Math.max(subArraySum, subArraySumMax);
        }

        return subArraySumMax;
    }

    static int subSequence(List<Integer> arr){

        int sum = 0;
        int min = Integer.MIN_VALUE;

        for(int a : arr) {
            sum += a > 0 ? a : 0;

            if(a < 0) min = Math.max(min, a);
        }

        return sum == 0 ? min : sum;
    }

}
