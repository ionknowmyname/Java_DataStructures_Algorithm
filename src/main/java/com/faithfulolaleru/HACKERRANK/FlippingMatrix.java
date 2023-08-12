package com.faithfulolaleru.HACKERRANK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlippingMatrix {

    /*
    *    https://www.youtube.com/watch?v=1jGAutnHuYM
    *
    *       EXPLANATION
    *
    *       Don't flip, just take all the possible values of the top left index that you coulda gotten after flipping,
    *       take the maximum of each and then sum. E.g for a 2x2 array (n=1), the top left element is the only one to
    *       return. For 4x4 array(n=2) its the top left 2x2 array that for each element we wanna find the max element
    *       that could be there. So for each index in the 2x2, save all the 4 possible values in an array, then pick
    *       max, at the end of the day, sum all 4 maxes and return. If n=3, it'll have be 6x6 array, and wed be summing
    *       up top 3x3 array
    *
    * */

    public static void main(String[] args) {
        List<List<Integer>> toTest = new ArrayList<>();

        toTest.add(Arrays.asList(112, 42, 83, 119));
        toTest.add(Arrays.asList(56, 125, 56, 49));
        toTest.add(Arrays.asList(15, 78, 101, 43));
        toTest.add(Arrays.asList(62, 98, 114, 108));

        System.out.println(flippingMatrix(toTest));
        System.out.println(flippingMatrix2(toTest));
        System.out.println(flippingMatrix3(toTest));

        // ans should be 414
    }


    // working
    // using integer array
    public static int flippingMatrix(List<List<Integer>> matrix) {

        int finalTotal = 0; int localMax = 0;

        int n = matrix.size() / 2;
        int fullSize = matrix.size() - 1;  // can use either this or below
        int size = matrix.size();  // if you use this, just  -1 in for-loop

        Integer[] mirrorNumbers = new Integer[4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // get all the 4 numbers that can be rotated amongst themselves to get the max for that square/index
                int a1 = matrix.get(i).get(j);
                int a2 = matrix.get(size - i - 1).get(j);
                int a3 = matrix.get(i).get(size - j - 1);
                int a4 = matrix.get(size - i - 1).get(size - j - 1);

                // use arrays instead of arraylist so that on each loop, it'll replace previous values
                mirrorNumbers[0] = a1;
                mirrorNumbers[1] = a2;
                mirrorNumbers[2] = a3;
                mirrorNumbers[3] = a4;

                Arrays.sort(mirrorNumbers, Collections.reverseOrder());

                localMax = mirrorNumbers[0];

                finalTotal += localMax;
            }
        }

        return finalTotal;
    }

    // using arraylist
    // also working
    public static int flippingMatrix2(List<List<Integer>> matrix) {

        int finalTotal = 0; int localMax = 0;

        int n = matrix.size() / 2;
        int fullSize = matrix.size() - 1;  // can use either this or below
        int size = matrix.size();  // if you use this, just  -1 in for-loop

        List<List<Integer>> mirrorNumbers2 = new ArrayList<>(4);  // didn't even need this

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // get all the 4 numbers that can be rotated amongst themselves to get the max for that square/index
                int a1 = matrix.get(i).get(j);
                int a2 = matrix.get(fullSize - i).get(j);
                int a3 = matrix.get(i).get(fullSize - j);
                int a4 = matrix.get(fullSize - i).get(fullSize - j);

                List<Integer> localList = new ArrayList<>();
                localList.add(a1);
                localList.add(a2);
                localList.add(a3);
                localList.add(a4);

                // Collections.sort(localList, Collections.reverseOrder());

                // instead of sorting, just pick max
                localMax = localList.stream().max((a, b) -> a - b).get();

                finalTotal += localMax;
            }
        }

        return finalTotal;
    }

    // no need for extra array
    // also working
    public static int flippingMatrix3(List<List<Integer>> matrix) {

        int finalTotal = 0; int localMax = 0; // Integer.MIN_VALUE

        int n = matrix.size() / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // get all the 4 numbers that can be rotated amongst themselves to get the max for that square/index
                int a1 = matrix.get(i).get(j);
                int a2 = matrix.get((2 * n) - i - 1).get(j);
                int a3 = matrix.get(i).get((2 * n) - j - 1);
                int a4 = matrix.get((2 * n) - i - 1).get((2 * n) - j - 1);

                // find max 1 by 1
//                localMax = Math.max(localMax, a1);
//                localMax = Math.max(localMax, a2);
//                localMax = Math.max(localMax, a3);
//                localMax = Math.max(localMax, a4);

                localMax = Math.max(a1, Math.max(a2, Math.max(a3, a4)));

                finalTotal += localMax;
            }
        }

        return finalTotal;
    }
}
