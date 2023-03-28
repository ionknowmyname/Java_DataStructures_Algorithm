package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EasyStringQuestions {

    public static void main(String[] args) {
//        System.out.println(isPalindromeNumber(121));
//        System.out.println(isPalindromeNumber(-121));
        System.out.println(isAnagram2("anagram", "nagaram"));
    }



    // 9. Palindrome Number
    public static boolean isPalindromeNumber(int x) {
        String s = String.valueOf(x);

        int i = 0;
        int j = s.length() - 1;

        while(i <= j) {
            if(s.charAt(i) != s.charAt(j)) return false;

            i++;
            j--;
        }

        return true;
    }

    // 409. Longest Palindrome
    public static int longestPalindrome(String s) {
        int oddCount = 0;
        char[] sChar = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < sChar.length; i++) {
//            if(map.containsKey(sChar[i])) map.put(sChar[i], map.get(sChar[i]) + 1);
//            else map.put(sChar[i], 1);

            map.put(sChar[i], map.getOrDefault(sChar[i], 0) + 1);

            if(map.get(sChar[i]) % 2 == 1) {  // appears odd times
                oddCount++;
            } else {   // appears even times, reduce the odd count
                oddCount--;
            }
        }

        if (oddCount > 1) {
            return s.length() - oddCount + 1;
            // + 1 coz you can use one of the characters that has no pair to be in the middle of your palindrome
            // so one of the Char that appear odd times would count
        }

        // if no odd count, the whole string a palindrome
        return s.length();

    }

    // 242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {   // populate map with characters from s
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch,0) + 1);
        }
        for(int i = 0; i < t.length(); i++) { // use characters from map
            char ch = t.charAt(i);
            if(map.get(ch) != null) {   // character exists in map
                if(map.get(ch) == 1) {    // char in map only once, after using, remove from map
                    map.remove(ch);
                } else {     // char appears more than once, so just reduce frequency
                    map.put(ch, map.get(ch) - 1);
                }
            } else { // if character don't exist in map, means a new character is in t that's not in s; so not anagram
                return false;
            }
        }
        return map.isEmpty();
    }

    public static boolean isAnagram2(String s, String t) {
        char[] sArr = s.toCharArray();
        Arrays.sort(sArr);
        String sNew = new String(sArr);

        char[] tArr = t.toCharArray();
        Arrays.sort(tArr);
        String tNew = new String(tArr);

        if(tNew.equalsIgnoreCase(sNew)) return true;

        return false;
    }

    // 125. Valid Palindrome
    public boolean isPalindrome(String s) {
        String toLowerAndAlphaNum = s.toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "");  // replace anything not matching regex with empty string

        int leftPointer = 0;
        int rightPointer = toLowerAndAlphaNum.length() - 1;

        while(leftPointer <= rightPointer) {
            if(toLowerAndAlphaNum.charAt(leftPointer++) != toLowerAndAlphaNum.charAt(rightPointer--)) {
                return false;
            }
        }
        return true;
    }

    // 20. Valid Parentheses
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch: s.toCharArray())
        {
            switch (ch)
            {
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(') {
                        // if the stack is empty then it means string has no open bracket.
                        return false;
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }

        // return true only if the stack is empty.
        // if stack is not empty that means we have unused brackets.

        return stack.isEmpty();
    }
}
