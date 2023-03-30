package com.faithfulolaleru.LEETCODE;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {



    // 424. Longest Repeating Character Replacement
    // sliding window
    public int characterReplacement(String s, int k) {
        int N = s.length();
        int start = 0, maxLength = 0, maxCount = 0;
        int[] charCounts = new int[26]; // each index keeps track of the count of each character


        for (int end = 0; end < N; end++) {
            charCounts[s.charAt(end) - 'A']++;  // increment count
            // i.e if charAt(end) is C, increase charCounts[C - A] by 1, which is charCounts[2]
            // ASCII code for A is 65, C is 67, so 65-67 which is 2, charCounts[2]
            // also if you follow, index 0, of charCounts would be A, 1 would be B, 2 would be C

            int currentCharCount = charCounts[s.charAt(end) - 'A'];
            maxCount = Math.max(maxCount, currentCharCount);
            // updating the max count, which would be the most number of times a letter appears

            while((end - start + 1) - maxCount > k) {
                // end - start is regular window, maxCount is count of letters that are same
                // so (end - start - maxCount) accounts for letters that are different from the letter
                // that owns maxCount & that needs tp be changed to find max repeating substring within our window
                // + 1 is coz we adding a new letter on each loop

                // so if we enter this while, it means we are at more than no. of times we can exchange characters
                // so we keep moving the left pointer forward until we are less than k

                charCounts[s.charAt(start) - 'A']--;
                // first we reduce the count of the letter at the left pointer (start)

                start++; // then you move the actual left pointer forward
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }


    // using hashmap to track frequency of integer array
    public int characterReplacement2(String s, int k) {

        char[] arr = s.toCharArray();

        int left = 0, right = 0, N = arr.length;
        int maxLength = 0, mostFreqCount = 0;

        Map<Character, Integer> map = new HashMap<>();

        while(right < N) {
            // add each letter to hashmap & increase its frequency
            // then update most frequent count

            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            mostFreqCount = Math.max(mostFreqCount, map.get(arr[right]));

            // shrink the window by moving left pointer forward if we need to do more than k replacements
            if((right - left + 1) - mostFreqCount > k) {
                map.put(arr[left], map.get(arr[left]) - 1);
                left++;
            }

            // Calculate MaxLength then move right forward
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
