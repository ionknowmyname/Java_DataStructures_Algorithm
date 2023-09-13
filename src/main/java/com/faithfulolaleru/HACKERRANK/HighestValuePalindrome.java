package com.faithfulolaleru.HACKERRANK;

public class HighestValuePalindrome {



    public static String highestValuePalindrome(String s, int n, int k) {

        char[] arr = s.toCharArray();
        int left = 0;
        int right = n - 1;
        int changes = 0;

        while (left < right) {
            if (arr[left] != arr[right]) {
                char maxChar = (char) Math.max(arr[left], arr[right]);
                arr[left] = maxChar;
                arr[right] = maxChar;
                changes++;
            }

            left++;
            right--;
        }

        if (changes > k) {
            return "-1";
        }

        // reset left & right
        left = 0;
        right = n - 1;

        while (left <= right) {
            if (left == right) {  // at the middle
                if (k - changes > 0) {   // can still make at least 1 change
                    arr[left] = '9';    //  set middle number to 9, that way its still palindromic
                }
            } else {
                if (arr[left] < '9') {
                    if (k - changes >= 2 && arr[left] == s.charAt(left) && arr[right] == s.charAt(right)) {

                        arr[left] = '9';
                        arr[right] = '9';
                        changes += 2;
                    } else if (k - changes >= 1 && s.charAt(left) != s.charAt(right)) {

                        arr[left] = '9';
                        arr[right] = '9';
                        changes++;
                    }
                }
            }

            left++;
            right--;
        }

        return new String(arr);
    }
}
