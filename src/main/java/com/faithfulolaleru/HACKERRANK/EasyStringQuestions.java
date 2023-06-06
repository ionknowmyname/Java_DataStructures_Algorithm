package com.faithfulolaleru.HACKERRANK;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EasyStringQuestions {


    public static void main(String[] args) {

        // System.out.println(minimumNumber(4, "aA!2"));
        // System.out.println(minimumNumber(3, "Ab1"));

        // System.out.println(gradingStudents(List.of(73, 67, 38, 33)));  // 75, 67, 40, 33

        // System.out.println(pangrams("We promptly judged antique ivory buckles for the next prize"));
        System.out.println(pangrams("qmExzBIJmdELxyOFWv LOCmefk TwPhargKSPEqSxzveiun"));   // pangram

    }


    // Recursive Digit Sum
    // my solution, failed some tests
    public static int superDigit(String n, int k) {
        long temp = findSumRecursive2(Long.parseLong(n)) * k;

        if(temp>9){
            temp = findSumRecursive2(temp);
        }

        return (int) temp;

    }

    // passed all tests
    public static int superDigit2(String n, int k) {
        long sum = 0;

        for (int i = 0; i < n.length(); i++) {
            sum += Long.parseLong(String.valueOf(n.charAt(i)));
        }

        sum *= k;

        if(sum < 10) return (int) sum;

        return superDigit2(String.valueOf(sum), 1);
    }

    static long findSumRecursive2(long num) {
        if(num < 10) return (int) num;

        long sum = 0;
        while(num > 0) {
            sum += num % 10;
            num = num / 10;
        }

        return findSumRecursive2(sum);
    }

    static long findSumRecursive(String num) {
        if(num.length() == 1) {
            return Integer.parseInt(num);
        }

        int sum =0;
        for(int i = 0; i < num.length(); i++){
            sum += Integer.parseInt(String.valueOf(num.charAt(i)));
        }

        return findSumRecursive(String.valueOf(sum));
    }

    public static int camelcase(String s) {
        int counter = 0;

        // another way is to use ascii integer for uppercase A-Z which is 65-90
        // int num = (int) char;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if(Character.isUpperCase(current)) counter++;
        }

        return counter + 1;   // + 1 coz the first word would be in small letter, bt still a word
    }

    // passes all tests, did all myself
    public static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong

        String numbers = "0123456789";
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special_characters = "!@#$%^&*()-+";

        char[] numArray = numbers.toCharArray();
        char[] lCaseArray = lower_case.toCharArray();
        char[] uCaseArray = upper_case.toCharArray();
        char[] sCharsArray = special_characters.toCharArray();

        int index = 0, changeRequired = 0;
        boolean numPresent = false;
        boolean lCasePresent = false;
        boolean uCasePresent = false;
        boolean sCharPresent = false;

        for (int i = 0; i < password.length(); i++) {
            char current  = password.charAt(i);

            // for numbers
            index = 0;  // reset index for the next letter in password
            while(index < numbers.length()) {
                if(current == numArray[index]) {  // condition met
                    numPresent = true;
                    break;
                } else {
                    index++;
                }
            }

            // for lowercase
            index = 0;   // reset index
            while(index < lower_case.length()) {
                if(current == lCaseArray[index]) {  // condition met
                    lCasePresent = true;
                    break;
                } else {
                    index++;
                }
            }

            // for uppercase
            index = 0;   // reset index
            while(index < lower_case.length()) {
                if(current == uCaseArray[index]) {  // condition met
                    uCasePresent = true;
                    break;
                } else {
                    index++;
                }
            }

            // for special chars
            index = 0;   // reset index
            while(index < special_characters.length()) {
                if(current == sCharsArray[index]) {  // condition met
                    sCharPresent = true;
                    break;
                } else {
                    index++;
                }
            }

        }

        if(numPresent == false) {  // for each one not present, when we add it, our length is now increased
            changeRequired++;
            n++;
        }
        if(lCasePresent == false) {
            changeRequired++;
            n++;
        }
        if(uCasePresent == false) {
            changeRequired++;
            n++;
        }
        if(sCharPresent == false) {
            changeRequired++;
            n++;
        }

        // check if its less than 6 chars
        if(n < 6) changeRequired += (6 - n);


        return changeRequired;
    }

    public static int minimumNumber2(int n, String password) {
        // Return the minimum number of characters to make the password strong

        String numbers = "0123456789";
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special_characters = "!@#$%^&*()-+";



        int changeRequired = 0;
        boolean numPresent = false;
        boolean lCasePresent = false;
        boolean uCasePresent = false;
        boolean sCharPresent = false;

        for (int i = 0; i < password.length(); i++) {
            char current  = password.charAt(i);

            if(Character.isDigit(current)) {
                numPresent = true;
                continue;
            }
            if(Character.isLowerCase(current)) {
                lCasePresent = true;
                continue;
            }
            if(Character.isUpperCase(current)) {
                uCasePresent = true;
                continue;
            }
            if(special_characters.indexOf(current) != -1) {  // char is present
                sCharPresent = true;
                continue;
            }

        }

        if(numPresent == false) {  // for each one not present, when we add it, our length is now increased
            changeRequired++;
            n++;
        }
        if(lCasePresent == false) {
            changeRequired++;
            n++;
        }
        if(uCasePresent == false) {
            changeRequired++;
            n++;
        }
        if(sCharPresent == false) {
            changeRequired++;
            n++;
        }

        // check if its less than 6 chars
        if(n < 6) changeRequired += (6 - n);


        return changeRequired;
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        List<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            int currGrade = grades.get(i);

            if(currGrade < 38) {
                toReturn.add(i, currGrade);
            } else {
                int x = currGrade % 5;
                if(x == 0) {   // is exactly a multiple of 5
                    toReturn.add(i, currGrade);
                } else if(x >= 3) {
                    // toReturn.add(i, 5 * (Math.round(currGrade / 5)));  // this rounds up to lower 5
                    toReturn.add(i, currGrade + 5 - x);  // this rounds up to upper 5
                } else {  // cannot upgrade score
                    toReturn.add(i, currGrade);
                }
            }
        }

        return toReturn;
    }

    public static String pangrams(String s) {
        if(s.length() < 26) return "not pangram";

        String sLow = s.toLowerCase(Locale.ROOT);

        // can also use map of characters, map a-z, then use the string s, each time a char in string s
        // is used, remove all occurences from the map for that char, then if map isn't empty, its not a pangram

        String alphabets = "abcdefghijklmnopqrstuvwxyz";

        int index1 = 0, index2 = 0;

        while(index1 < alphabets.length()) {

            char currentAlphabet = alphabets.charAt(index1);
            char currentS = sLow.charAt(index2);

            if(currentAlphabet == currentS) {
                index1++;  // go to next char in alphabet
                index2 = 0;   // to start comparing from beginning of S again
                continue;
            } else {
                index2++;  // go to next char String s
            }

            if(index2 > s.length() - 1) {
                // we looped all letters in s and didn't find a corresponding letter in s for an alphabet
                return "not pangram";
            }

        }

        return "pangram";
    }
}
