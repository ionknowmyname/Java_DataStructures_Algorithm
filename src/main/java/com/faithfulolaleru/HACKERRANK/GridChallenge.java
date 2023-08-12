package com.faithfulolaleru.HACKERRANK;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class GridChallenge {

    public static void main(String[] args) {
        System.out.println(gridChallenge2(Arrays.asList("abc", "ade", "efg")));
    }

    // my solution
    public static String gridChallenge(List<String> grid) {
        // Write your code here

//        List<String> arranged = new ArrayList<>();
//
//        for (int i = 0; i < grid.size(); i++) {
//            arranged.add(rearrangeString(grid.get(i)));
//        }

//        String toReturn = "";
//
//        List<List<Integer>> gridToAscii = new ArrayList<>();
//
//        for (String input : grid) {
//            gridToAscii.add(stringToSortedAscii(input));
//        }



//        for (int i = 0; i < gridToAscii.size() - 2; i++) {
//            if(gridToAscii.get(i).get(i) < gridToAscii.get(i + 1).get(i)
//                    && gridToAscii.get(i + 1).get(i) < gridToAscii.get(i + 2).get(i)) {
//
//                toReturn = "YES";
//            } else {
//                toReturn = "NO";
//            }

            /*for (int j = i; j < gridToAscii.get(i).size(); j++) {
                if(gridToAscii.get(i).get(j) < gridToAscii.get().get()
                    && gridToAscii.get().get() < gridToAscii.get().get()) {

                    toReturn = "YES";
                } else {
                    toReturn = "NO";
                }
            }
        }

        return toReturn;*/

            return null;
    }

    // correct solution
    public static String gridChallenge2(List<String> grid) {

        for (int i = 0; i < grid.size(); i++) {
            grid.set(i, rearrangeString2(grid.get(i)));  // strings in grid now arranged
        }

//        for (int i = 0; i < grid.get(0).length(); i++) {
//            for (int j = 0; j < grid.size()-1; j++) {
//                if (grid.get(j).charAt(i) > grid.get(j+1).charAt(i)) {
//                    return "NO";
//                }
//            }
//        }
//        return "YES";

        for (int i = 0; i < grid.size()-1; i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) > grid.get(i+1).charAt(j)) {
                    return "NO";
                }
            }
        }
        return "YES";
    }


    public static List<Integer> stringToSortedAscii(String input) {
        List<Integer> convert = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            int ascii = (int) input.charAt(i);

            convert.add(ascii);
        }

        Collections.sort(convert);

        return convert;
    }

    public static String rearrangeString(String input) {

        List<Integer> convert = new ArrayList<>();
        // int[] convert2 = new int[input.length()];
        // StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            int ascii = (int) input.charAt(i);

            convert.add(ascii);
            // convert2[i] = ascii;
        }

        Collections.sort(convert);  // now ascii integers are sorted, now convert back to a-z
        // Arrays.sort(convert2);


        // return toReturn.toString();

        return null;
    }

    public static String rearrangeString2(String input) {

        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }
}
