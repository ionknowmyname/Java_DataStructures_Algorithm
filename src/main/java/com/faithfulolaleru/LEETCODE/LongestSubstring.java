package com.faithfulolaleru.LEETCODE;

import java.util.*;

public class LongestSubstring {

    /*
        1. Longest substring with at most 2 distinct characters
        2. Longest substring with no repeating characters
        SLIDING WINDOW TECHNIQUE (Variable sized window)
    */



    public static void main(String[] args) {

        System.out.println(findLongestSubString2Distinct("abcabcbb"));
        System.out.println(findLongestSubString2Distinct("bbbbb"));
        System.out.println(findLongestSubString2Distinct("pwwkew"));

        System.out.println("_______________________________");
        System.out.println(findLongestSubStringNoRepeat("abcabcbb"));
        System.out.println(findLongestSubStringNoRepeat("bbbbb"));
        System.out.println(findLongestSubStringNoRepeat("pwwkew"));
    }



    public static Integer findLongestSubString2Distinct(String word) {

        if(word.length() < 2){
            return word.length();
        }

        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0; int left = 0;

        for (int right = 0; right < word.length(); right++) {
            Character rChar = word.charAt(right);

            if(!map.containsKey(rChar)) {
                map.put(rChar, 1);  // first time in map, so frequency is 1
            } else {
                map.put(rChar, map.get(rChar) + 1);  // update the occurence to +1
            }

            while(map.size() > 2) {
                // more than allowed distinct chars, so move left pointer to the right by 1
                // to remove 1 distinct key-value pair from map

                Character lChar = word.charAt(left);

                if(map.get(lChar) > 1) {
                    map.put(lChar, map.get(lChar) - 1);
                } else {
                    map.remove(lChar);
                }

                left++;
            }

            maxLength = Math.max(maxLength, (right - left) + 1);
        }

        return maxLength;
    }

    // 3. Longest Substring Without Repeating Characters
    public static Integer findLongestSubStringNoRepeat(String word) {

        if(word.length() < 2){
            return word.length();
        }

        Set<Character> set = new HashSet<>();
        int leftPointer = 0, rightPointer = 0, maxLength = 0;


        /*  ORIGINAL SOLUTION, it works, passes all tests

            while (rightPointer < word.length()) {
                if (!set.contains(word.charAt(rightPointer))) {
                    set.add(word.charAt(rightPointer++));
                    maxLength = Math.max(maxLength, set.size());
                } else {
                    set.remove(word.charAt(leftPointer++));
                }
            }

        */

        // this below fails some tests

        while (rightPointer < word.length()) {
            if (!set.contains(word.charAt(rightPointer))) {
                set.add(word.charAt(rightPointer));
                rightPointer++;
                maxLength = Math.max(maxLength, set.size()); // or use rightPointer - leftPointer instead of set.size()
            } else {
                set.remove(word.charAt(leftPointer));
                leftPointer++;
                rightPointer++;
            }
        }

        return maxLength;
    }
}
