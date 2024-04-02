package com.faithfulolaleru.HACKERRANK;

import java.util.*;

public class EasyArrayQuestions {

    public static void main(String[] args) {

        List<Integer> testList = Arrays.asList(63, 25, 73, 1, 98, 73, 56, 84, 86, 57, 16, 83, 8, 25, 81, 56, 9, 53, 98, 67, 99, 12, 83, 89, 80, 91, 39, 86, 76, 85, 74, 39, 25, 90, 59, 10, 94, 32, 44, 3, 89, 30, 27, 79, 46, 96, 27, 32, 18, 21, 92, 69, 81, 40, 40, 34, 68, 78, 24, 87, 42, 69, 23, 41, 78, 22, 6, 90, 99, 89, 50, 30, 20, 1, 43, 3, 70, 95, 33, 46, 44, 9, 69, 48, 33, 60, 65, 16, 82, 67, 61, 32, 21, 79, 75, 75, 13, 87, 70, 33);
        // System.out.println(countingSort(testList));

        minimumBribes(Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8));   // should return 1
        minimumBribes(Arrays.asList(4, 1, 2, 3));   // should return too chaotic
    }



    // Left Rotation
    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        // Write your code here

        int counter = 0;
        List<Integer> toReturn = new ArrayList<>();

        if(d == 0) return arr;

        for (int i = d; i < arr.size(); i++) {
            toReturn.add(arr.get(i));
        }

        while(counter < d) {
            toReturn.add(arr.get(counter));
            counter++;
        }

        return toReturn;
    }


    // Counting Sort 1
    public static List<Integer> countingSort(List<Integer> arr) {
        List<Integer> frequencyArray = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            frequencyArray.add(0);
        }

        for (int i = 0; i < arr.size(); i++) {
            int valueAtI = arr.get(i);
            frequencyArray.set(valueAtI, frequencyArray.get(valueAtI) + 1);
        }

        return frequencyArray;
    }

    // New Year Chaos
    // fails 1 out of 3 tests
    public static void minimumBribes(List<Integer> q) {

        int forwardMoveCounter = 0;

        for (int i = 1; i < q.size(); i++) {
            if(q.get(i) < q.get(i - 1)) {
                int x = q.get(i - 1);
                int y = q.get(i);

                if(y + 3 <= x) {   // or if (x-y) >= 3
                    System.out.println("Too chaotic");
                    break;
                }

                if(y + 1 == x) forwardMoveCounter++;
                if(y + 2 == x) forwardMoveCounter += 2;
            }
        }

        if(forwardMoveCounter > 0) System.out.println(forwardMoveCounter);
    }

    // also failed 1/3 tests
    public static void minimumBribes2(List<Integer> q) {

        // 5 1 2 3 7 8 6 4
        int forwardMoveCounter = 0;

        for (int i = 0; i < q.size() - 1; i++) {
            if(q.get(i) > q.get(i + 1)) {
                int x = q.get(i);
                int y = q.get(i + 1);

                if((x - y) >= 3) {   // or if (x-y) >= 3
                    System.out.println("Too chaotic");
                    break;
                }

                if(y + 1 == x) forwardMoveCounter++;
                if(y + 2 == x) forwardMoveCounter += 2;
            }
        }

        if(forwardMoveCounter > 0) System.out.println(forwardMoveCounter);
    }

    public static void minimumBribes3(List<Integer> q) {

        int count = 0;
        boolean chaotic = false;

        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) - (i + 1) > 2) {
                // q.get(i) should = (i + 1), if difference > 2, means a number jumped more than 2 places to take that position
                chaotic = true;
                break;
            }

            // basically start at the number before i and stop before i
            for (int j = Math.max(0, q.get(i) - 2); j < i; j++) {
                // j pointer would be before i pointer, at each j position up to i, if
                // j is greater than i, we keep increasing our count, that coz that's
                // another position the number at index j jumped
                if (q.get(j) > q.get(i)) {
                    count++;
                }
            }
        }

        if (chaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(count);
        }
    }

    // the hurdle race
    // passed once on first try; my code
    public static int hurdleRace(int k, List<Integer> height) {
        if (height.size() < 1) return 0;

        int doseCounter =  0;

        for (int i = 0; i < height.size(); i++) {
            if (height.get(i) > k) {
                int tempDoseCounter = height.get(i) - k;
                if (doseCounter < tempDoseCounter) doseCounter = tempDoseCounter;
            }
        }

        return doseCounter;
    }

    public static int hurdleRace2(int k, List<Integer> height) {
        int requiredDoses = 0;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 0; i < height.size(); i++) {
            maxHeight = Math.max(height.get(i), maxHeight);
        }

        if (maxHeight >= k) {
            requiredDoses = maxHeight - k;
        }

        return requiredDoses;
    }

    public static int hurdleRace3(int k, List<Integer> height) {
        int maxHeight = height.stream().max(Comparator.naturalOrder()).orElse(0);

        return k >= maxHeight ? 0 : maxHeight - k;
    }

    //  diagonal difference
    public static int diagonalDifference(List<List<Integer>> arr) {
        int firstDiagonalSum = 0; int secondDiagonalSum = 0;

        for (int i = 0; i < arr.size(); i++) {
            firstDiagonalSum += arr.get(i).get(i);
            secondDiagonalSum += arr.get(i).get(arr.size() - 1 - i);
        }

        return Math.abs(firstDiagonalSum - secondDiagonalSum);
    }

    // plus minus
    public static void plusMinus(List<Integer> arr) {
        float sumPlus = 0;
        float sumMinus = 0;
        float sumZero = 0;

        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > 0) sumPlus++;
            if(arr.get(i) < 0) sumMinus++;
            if(arr.get(i) == 0) sumZero++;
        }

        System.out.println(String.format("%.6f", sumPlus / arr.size()));
        System.out.println(String.format("%.6f", sumMinus / arr.size()));
        System.out.println(String.format("%.6f", sumZero / arr.size()));



    }
}
