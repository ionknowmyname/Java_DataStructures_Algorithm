package com.faithfulolaleru.HACKERRANK;

import java.util.*;

public class EasyArrayQuestions {

    public static void main(String[] args) {

        List<Integer> testList = Arrays.asList(63, 25, 73, 1, 98, 73, 56, 84, 86, 57, 16, 83, 8, 25, 81, 56, 9, 53, 98, 67, 99, 12, 83, 89, 80, 91, 39, 86, 76, 85, 74, 39, 25, 90, 59, 10, 94, 32, 44, 3, 89, 30, 27, 79, 46, 96, 27, 32, 18, 21, 92, 69, 81, 40, 40, 34, 68, 78, 24, 87, 42, 69, 23, 41, 78, 22, 6, 90, 99, 89, 50, 30, 20, 1, 43, 3, 70, 95, 33, 46, 44, 9, 69, 48, 33, 60, 65, 16, 82, 67, 61, 32, 21, 79, 75, 75, 13, 87, 70, 33);
        System.out.println(countingSort(testList));
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

}
