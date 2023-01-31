package com.faithfulolaleru.HACKERRANK;

import java.util.*;

public class EasyArrayQuestions {

    public static void main(String[] args) {

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

}
