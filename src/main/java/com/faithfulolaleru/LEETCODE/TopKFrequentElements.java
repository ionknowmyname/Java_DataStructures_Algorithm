package com.faithfulolaleru.LEETCODE;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));
    }


    // 347. Top K Frequent Elements
    // my solution  // passes all tests
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] toReturn = new int[k];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Hashmap to list then sort list by values from highest to lowest
        List<Map.Entry<Integer, Integer>> hashMapList = new ArrayList<>(map.entrySet());
        Collections.sort(hashMapList, Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        // Collections.sort(hashMapList, Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int counter = 0;
        while(counter < k) {
            toReturn[counter] = hashMapList.get(counter).getKey();
            counter++;
        }

        return toReturn;
    }
}
