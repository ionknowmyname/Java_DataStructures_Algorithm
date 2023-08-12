package com.faithfulolaleru.HACKERRANK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseArrays {

    /*
    *   List of strings, and list of queries, for each query string, find how many times the
    *   the query string can be found in the list of strings, return an integer list with how
    *   many times each string was found in the list
    *
    * */

    public static void main(String[] args) {
        List<String> a = List.of("aba", "baba", "aba", "xyz");
        List<String> b = List.of("aba", "xyz", "ab");
        System.out.println(matchingStrings(a, b));
    }



    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Write your code here

        List<Integer> toReturn = new ArrayList<>();
        Map<String, Integer> newMap = new HashMap<>();

        for(int i = 0; i < stringList.size(); i++){
            if(newMap.containsKey(stringList.get(i))) {
                newMap.put(stringList.get(i), newMap.get(stringList.get(i)) + 1);
            } else {
                newMap.put(stringList.get(i), 1);
            }
        }

        for(int j = 0; j < queries.size(); j++){
            if(newMap.containsKey(queries.get(j))) {
                toReturn.add(newMap.get(queries.get(j)));
            } else {
                toReturn.add(0);
            }
        }

        return toReturn;
    }
}
