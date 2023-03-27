package com.faithfulolaleru.HACKERRANK;

public class EasyStringQuestions {


    public static void main(String[] args) {

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
}
