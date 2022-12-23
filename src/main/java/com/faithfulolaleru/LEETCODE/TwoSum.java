package com.faithfulolaleru.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    /*
    *   Sum any two numbers in an array to equal a target,
    *   when you find the two numbers, return their indexes in the list
    */


    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 7, 3};
        List<Integer> arr2 = List.of(1, 5, 7, 3);

        List<Integer> twoSum = TwoSum.findTwoSum(arr2, 4);
        System.out.println(twoSum);
    }


    public static List<Integer> findTwoSum(List<Integer> nums, int target) {

        Map<Integer, Integer> newMap = new HashMap<>();
        List<Integer> returnList = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums.get(i);

            if(newMap.containsKey(complement)) {
                returnList.add(newMap.get(complement));
                returnList.add(i);

                return returnList;
            }

            newMap.put(nums.get(i), i);
        }

        // no such two numbers that add up to target present in num list
        return null;
    }
}
