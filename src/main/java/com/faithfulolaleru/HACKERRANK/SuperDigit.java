package com.faithfulolaleru.HACKERRANK;

public class SuperDigit {

    public static void main(String[] args) {
        // System.out.println(SuperDigit.superDigit("9875", 4));
        System.out.println(SuperDigit.superDigit2("148", 3));
    }


    // my first solution
    public static int superDigit(String n, int k) {
        // Write your code here

        // String total = n.repeat(k);
        Integer num = Integer.parseInt(n);
        // int total = num * k;
        int sum = 0, sum2 = 0;

        for (int i = 0; i < n.length(); i++) {
            sum += num % 10;
            num = num / 10;
        }

        int totalSum = sum * k;
        String length = String.valueOf(totalSum);
        for (int i = 0; i < length.length(); i++) {
            sum2 += totalSum % 10;
            totalSum = totalSum / 10;
        }

        if(String.valueOf(sum2).length() > 1) {  // run again
            int sum3 = 0;
            String length2 = String.valueOf(sum2);
            for (int i = 0; i < length2.length(); i++) {
                sum3 += sum2 % 10;
                sum2 = sum2 / 10;
            }

            return sum3;
        } else {
            return sum2;
        }

    }

    // correct solution
    public static int superDigit2(String n, int k) {

        long sum = 0;
        long num = Long.parseLong(n);

        for(int i = 0; i< n.length(); i++){
            // sum += Integer.valueOf(n.charAt(i) - 48);

            sum += num % 10;
            num = num / 10;
        }
        sum *= k;

        return (int) findSumRecursive2(sum);
    }

    public static int superDigit3(String n, int k) {

        long superDigit = findSumRecursive(n);
        long n1 = findSumRecursive2(superDigit);
        long k1 = findSumRecursive2(k);

        long result = n1 * k1;

        while (result / 10 != 0) {
            result = findSumRecursive2(result);
        }

        return (int) result;
    }

    static long findSumRecursive(String num) {  // adding up numbers from front
        if(num.length() == 1) {
            return Integer.parseInt(num);
        }

        int sum =0;
        for(int i = 0; i < num.length(); i++){
            sum += Integer.parseInt(String.valueOf(num.charAt(i)));
        }

        return findSumRecursive(String.valueOf(sum));
    }

    static long findSumRecursive2(long num) {  // adding up numbers from back using remainder method
        if(num < 10) return (int) num;

        long sum = 0;
        while(num > 0) {
            sum += num % 10;
            num = num / 10;
        }

        return findSumRecursive2(sum);
    }
}
