package com.faithfulolaleru.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class CanSum {

    /*
    *   return a boolean indicating if it is possible to generate the target sum using numbers
    *   from the array summed up together in any order.
    *
    *   You can use an element in the array as many times as needed
    *   target number & number in the array are non negative
    *
    *   Use Dynamic Programming
    *
    * */


    public static void main(String[] args) {
        System.out.println(canSum(7, new int[]{2, 3}));
        System.out.println(canSum(7, new int[]{5, 3, 4, 7}));
        System.out.println(canSum(7, new int[]{2, 4}));
        System.out.println(canSum(8, new int[]{2, 3, 5}));

        System.out.println();
        System.out.println(canSumDP(300, new int[]{7, 14}, new HashMap<>()));

        System.out.println(canSum(300, new int[]{7, 14}));


    }


    // regular recursive
    public static boolean canSum(int target, int[] nums) {
        if(target == 0) return true;  // take nothing from the nums array, and you'd sum up to 0
        if(target < 0) return false;  // another base case for recursive call; since no -ve num, so if
        // we encounter -ve num, it means the remainder was smaller than the num in nums chosen for that
        // iteration, so return false & move to next num

        for(int num : nums) {
            int remainder = target - num;

            if(canSum(remainder, nums) == true) return true;
        }

        return false;
    }

    // memoization  // Dynamic Programming
    // java solution not working, check js
    public static boolean canSumDP(int target, int[] nums, Map<Integer, Boolean> map) {

        if(map.containsKey(target)) return map.get(target);

        if(target == 0) return true;
        if(target < 0) return false;

        for(int num : nums) {
            int remainder = target - num;

            if(canSum(remainder, nums) == true) {
                map.put(target, true);  // save the result in map/memo before returning
                return true;
            }
        }

        map.put(target, false);  // also save the result in map/memo before returning
        return false;
    }
}
