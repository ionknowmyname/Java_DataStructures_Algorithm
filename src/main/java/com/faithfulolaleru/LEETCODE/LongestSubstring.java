package com.faithfulolaleru.LEETCODE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstring {

    /*
        Longest substring with at most 2 distinct characters
        SLIDING WINDOW TECHNIQUE (Variable sized window)
    */



    public static void main(String[] args) {

        System.out.println(findLongestSubStringLength("abcabcbb"));
        System.out.println(findLongestSubStringLength("bbbbb"));
        System.out.println(findLongestSubStringLength("pwwkew"));
    }



    public static Integer findLongestSubStringLength(String word) {

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
}
