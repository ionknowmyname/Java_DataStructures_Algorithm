package com.faithfulolaleru.HACKERRANK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlmostSorted {



    // works but don't understand the Reverse part
    public static void almostSorted(List<Integer> arr) {
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        int swapCount = 0;
        int segmentSize = 0;
        List<Integer> indices = new ArrayList<>();

        // Swap
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != sorted.get(i)) {
                swapCount++;

                // add the indexes to be swapped
                indices.add(i + 1); // + 1 coz counter in question starts from 1 not 0 like regular 0-indexed arrays
                // get the index of the value at i at its rightful sorted position
                indices.add(arr.indexOf(sorted.get(i)) + 1);   // same reason for + 1 here
            }
        }

        // Reverse
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                segmentSize++;
            } else {
                if (segmentSize > 1) {
                    List<Integer> startArr = new ArrayList<>(arr.subList(0, i - segmentSize));
                    List<Integer> subArr = new ArrayList<>(arr.subList(i - segmentSize, i + 1));
                    List<Integer> endArr = new ArrayList<>(arr.subList(i + 1, arr.size()));

                    Collections.reverse(subArr);
                    arr.clear();
                    arr.addAll(startArr);
                    arr.addAll(subArr);
                    arr.addAll(endArr);

                    indices.add(i + 1);
                    indices.add(i - segmentSize + 1);
                    break;
                }
                segmentSize = 0;
            }
        }

        if (swapCount == 2) {
            System.out.println("yes");
            System.out.println("swap " + indices.get(0) + " " + indices.get(1));
        } else if (arr.equals(sorted)) {
            System.out.println("yes");
            System.out.println("reverse " + indices.get(indices.size() - 1) + " " + indices.get(indices.size() - 2));
        } else {
            System.out.println("no");
        }
    }

    // not working at all at all
    public static void almostSorted2(List<Integer> arr) {

        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != sorted.get(i)) {
                indices.add(i);
            }
        }

        if (indices.size() == 2) {
            System.out.println("yes");
            System.out.println("swap " + indices.get(0) + 1 + " " + indices.get(1) + 1);
            // the +1 is coz according to the question, the first index is 1 not 0, so +1 to correct
        } else {
            boolean isReverse = true;

            for (int i = indices.get(0); i < indices.get(indices.size() - 1); i++) {
                if (arr.get(i) < arr.get(i + 1)) {
                    isReverse = false;
                    break;
                }
            }

            if (isReverse) {
                System.out.println("yes");
                System.out.println("reverse " + indices.get(0) + 1 + " " + indices.get(indices.size() - 1) + 1);
            } else {
                System.out.println("no");
            }
        }


    }

    // I understand better, could use some review
    public static void almostSorted3(List<Integer> arr) {

        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != sorted.get(i)) {
                indices.add(i + 1);
                // the +1 is coz according to the question, the first index is 1 not 0, so +1 to correct
            }
        }

        if (indices.size() == 0) {
            System.out.println("yes");
        } else if (indices.size() == 2) {
            System.out.println("yes");
            System.out.println("swap " + indices.get(0) + " " + indices.get(1));
        } else {
            for (int i = 0; i < indices.size() - 1; i++) {
                int current = indices.get(i);
                int next = indices.get(i + 1);

                if (arr.get(current - 1) < arr.get(next - 1)) {
                    // -1 coz we originally added +1 to it while storing
                    System.out.println("no");
                    return;
                }
            }

            System.out.println("yes");
            System.out.println("reverse "+ (indices.get(0)) + " " + (indices.get(indices.size() - 1)));
        }

    }
}
