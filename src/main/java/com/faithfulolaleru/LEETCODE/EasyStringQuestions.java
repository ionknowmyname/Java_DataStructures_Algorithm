package com.faithfulolaleru.LEETCODE;

import java.util.*;

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

    // working perfectly too
    public boolean isAnagram3(String s, String t) {
        Map<Character, Integer> track  = new HashMap<>();

        for(char item : s.toCharArray()) {
            track.put(item, track.getOrDefault(item, 0) + 1);
        }

        for(char item : t.toCharArray()) {
            if (track.containsKey(item) && track.get(item) > 0) track.put(item, track.get(item) - 1);
            else return false;
        }

        Collection<Integer> vlist = track.values();
        int final2 = vlist.stream().reduce(0, Integer::sum);

        return final2 == 0;
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

    // 678. Valid Parenthesis String
    public boolean checkValidString(String s) {
        Stack<Integer> stack = new Stack<>();   // Character
        Stack<Integer> starStack = new Stack<>();

        // first impl. failed some tests
        /* for(char ch: s.toCharArray()) {
            switch (ch) {
                case '*':
                    starStack.push(ch);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    if (!stack.isEmpty()) {  //   && stack.peek() == '('
                        stack.pop();
                    } else if (!starStack.isEmpty()) {
                        starStack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        } */

        // now pop the index into the stack not the actual chars
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') stack.push(i);
            else if(s.charAt(i) == '*') starStack.push(i);
            else {
                if(!stack.empty()) stack.pop();
                else if(!starStack.empty()) starStack.pop();
                else return false;
            }
        }

        // top for-loop guarantee ')' is properly matched i.e '('or '*' popped out for every ')';
        // now check for leftover '(' in stack


        /*  while (!stack.isEmpty() && !starStack.isEmpty()) {
            if (stack.peek() > starStack.peek()) {
                // if ( comes after *, there's no way it'll be symmetric & cancel out
                // it can only cancel out if * comes after ( so the * can be transformed to )
                return false;
            }
            // else they cancel out each other
            stack.pop();
            starStack.pop();
        } */

        while (!stack.isEmpty() && !starStack.isEmpty() && starStack.peek() > stack.peek()) {
            stack.pop();
            starStack.pop();
        }

        return stack.isEmpty();
    }

    // 383. Ransom Note
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            char current = magazine.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char current = ransomNote.charAt(i);

            if(!map.containsKey(current)) return false;

            if(map.get(current) > 1) { // occurs more than once
                map.put(current, map.get(current) - 1);
            } else {  // occurs once, so remove instead of setting frequency to 0
                map.remove(current);
            }

        }

        return true;
    }
}
