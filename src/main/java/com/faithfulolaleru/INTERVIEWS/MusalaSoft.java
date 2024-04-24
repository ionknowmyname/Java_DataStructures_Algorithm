package com.faithfulolaleru.INTERVIEWS;

import java.util.HashMap;
import java.util.Map;

public class MusalaSoft {

    // 3rd Interview; 2nd attempt; Question 1
    // passed 8/8 tests
    public static void main(String[] args) {
        System.out.println(compressedString("abaasass"));
        // expected response = aba2sas2
    }

    // for question 1
    public static String compressedString(String message) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char currentChar = message.charAt(0);

        for (int i = 1; i < message.length(); i++) {
            char nextChar = message.charAt(i);
            if (currentChar == nextChar) {
                count++;
            } else {
                compressed.append(currentChar);
                if (count > 1) {
                    compressed.append(count);
                }
                count = 1;
                currentChar = nextChar;
            }
        }

        compressed.append(currentChar);
        if (count > 1) {
            compressed.append(count);
        }

        return compressed.toString();
    }

    // 3rd Interview; 1st attempt Question 2 & 2nd attemt Question 2
    // passed 7/8 tests in 2nd attempt; didn't attempt question in 1st attempt
    public static void main1(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Reading input for the number of items (n), sort parameter, sort order, and X
        int n = Integer.parseInt(br.readLine());
        String[][] items = new String[n][3];
        for (int i = 0; i < n; i++) {
            items[i] = br.readLine().trim().split(" ");
        }
        int sortParameter = Integer.parseInt(br.readLine().trim());
        int sortOrder = Integer.parseInt(br.readLine().trim());
        int X = Integer.parseInt(br.readLine().trim());

        // Calling the fetchItemsToDisplay method
        String[] firstXItems = fetchItemsToDisplay(items, sortParameter, sortOrder, X);
        for (String item : firstXItems) {
            System.out.println(item);
        }

        br.close();
    }

    // for question 2
    public static String[] fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder, int X) {
        Arrays.sort(items, new Comparator<String[]>() {
            @Override
            public int compare(String[] item1, String[] item2) {
                if (sortOrder == 0) {
                    return item1[sortParameter].compareTo(item2[sortParameter]);
                } else {
                    return item2[sortParameter].compareTo(item1[sortParameter]);
                }
            }
        });

        String[] firstXItems = new String[X];
        for (int i = 0; i < X && i < items.length; i++) {
            firstXItems[i] = items[i][0];
        }

        return firstXItems;
    }



    // 3rd Interview; 2nd attempt; Question 3
    // passed 2/8 tests
    public static void main3(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int result = counting(s);
        System.out.println(result);
        br.close();
    }

    public static int counting(String s) {
        int count = 0;
        int currentOnes = 0;
        int currentZeros = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                currentZeros++;
                currentOnes = 0; // Reset ones count when encountering a zero
            } else { // c == '1'
                currentOnes++;
                count += Math.min(currentZeros, currentOnes); // Add count when both 0s and 1s are equal
            }
        }

        /*for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }

            if (zeroCount == oneCount) {
                count++;
            }
        }*/


        /*for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substr = s.substring(i, j);
                if (isValidSubstring(substr)) {
                    count++;
                }
            }
        }*/

        return count;
    }

    public static boolean isValidSubstring(String substr) {
        int countZeros = 0;
        int countOnes = 0;
        for (char ch : substr.toCharArray()) {
            if (ch == '0') {
                countZeros++;
            } else {
                countOnes++;
            }
            if (countZeros != countOnes) {
                return false;
            }
        }
        return true;
    }



    // 3rd Interview; 1st attempt; Question 1
    // passed 7/8 tests
    public static int getMin(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            Character current = s.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        count = Math.abs(map.getOrDefault('(', 0) - map.getOrDefault(')', 0));

        return count;
    }


}
