package com.faithfulolaleru.LEETCODE;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {

    // SLIDING WINDOW - Variable size
    // LEETCODE 340

    // my solution from .js, convert to java
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0)  return 0;

        int left = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            while(map.size() > k) {  // try if too
                // move left pointer forward
                char leftChar = s.charAt(left);

                if(map.get(leftChar) > 1) {   // more than 1 times, decrease count by 1
                    map.put(leftChar, map.get(leftChar) - 1);
                } else {  // appears only once, remove from map
                    map.remove(leftChar);
                }

                left++;
            }

            max = Math.max(max, (right - left) + 1);
        }

        return max;
    }

    // copied solution
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if (s == null || s.length() == 0)  return 0;

        int left = 0, right = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            char head = s.charAt(right++);   // expand right
            map.put(head, map.getOrDefault(head, 0) + 1);

            // keep calculating max till we get to window limit which is k
            if (map.size() <= k) max = Math.max(max, right - left);

            // more than k distinct chars, move left pointer forward
            if (map.size() > k) {
                char tail = s.charAt(left++);
                map.put(tail, map.get(tail) - 1);
                if (map.get(tail) == 0) map.remove(tail);
            }
        }

        return max;
    }
}
