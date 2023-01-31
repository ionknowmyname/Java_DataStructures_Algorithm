package com.faithfulolaleru.HACKERRANK;

public class CaesarCipher {

    public static void main(String[] args) {

        CaesarCipher.caesarCipher("abcdefghijklmnopqrstuvwxyz", 3);
    }


    public static String caesarCipher(String s, int k) {
        String toReturn = "";

        for (int i = 0; i < s.length(); i++) {
            toReturn = toReturn + getUpdatedAsciiChar(s.charAt(i), k % 26);
            // k % 26; in case the increment is greater than how many alphabets there are
        }

        System.out.println(toReturn);

        return toReturn;
    }

    static char getUpdatedAsciiChar(char ch, int incremental) {
        if (Character.isLetter(ch)) {
            int i = (int) ch;
            i = i + incremental;

            // uppercase ascii is from 65-90 for A-Z,
            // lowercase ascii is from 97-122 for a-z,

            if (Character.isUpperCase(ch)) {
                if (i > 90) { // rotate from Z back to A
                    int num = (int) ch + incremental;
                    i = num - 26;
                }
            } else if (i > 122) {  // rotate from z to a
                int num = (int) ch + incremental;
                i = num - 26;
            }
            return (char) i;
        }
        return ch;
    }
}
