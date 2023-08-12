package com.faithfulolaleru.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Fibonacci {


    public static void main(String[] args) {
        System.out.println(fibonacci(3));
//        System.out.println(fibonacci(5));
//        System.out.println(fibonacci(50));

//        System.out.println(fibonacciDynamicProgramming(3, new int[4]));
//        System.out.println(fibonacciDynamicProgramming(5, new int[6]));
//        System.out.println(fibonacciDynamicProgramming(50, new int[51]));

        System.out.println(fibonacciDP(3));
        System.out.println(fibonacciDP(5));
        System.out.println(fibonacciDP(50));
    }

    public static int fibonacci(int n){

        if(n < 0) return -1;
        if(n == 0 || n == 1){
            return n;  // fibonacci of 0 is 0, and of 1 is 1
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }


    public static int fibonacciDP(int n) {
        int memo[] = new int[n + 1];
        Arrays.fill(memo,-1);

        return fibonacciDynamicProgramming(n-1, memo) + fibonacciDynamicProgramming(n-2, memo);
    }

    // memoization
    public static int fibonacciDynamicProgramming(int n, int[] memo) {
        if(n < 0) return 0;
        if(n == 0 || n == 1) {
            memo[n] = 1;

            return memo[n];
        }

        if(memo[n] != -1) {  // if we've found fibonacci of a number, return it
            return memo[n];
        }

        int left = fibonacciDynamicProgramming(n - 1, memo);
        int right = fibonacciDynamicProgramming(n - 2, memo);
        memo[n] = left + right;

        return  memo[n];
    }

    // best DP solution with memoization
    public static int fibonacciDP2(int n, Map<Integer, Integer> memo) {
        if(n == 1) return 0;
        if(n == 2) return 1;

        if(!memo.containsKey(n)) {
            memo.put(n, fibonacciDP2(n - 1, memo) + fibonacciDP2(n - 2, memo));
        }

        return memo.get(n);
    }

    // best DP solution using tabulation, no recursion
    public static int fibonacciDP3(int n) {
        List<Integer> tb = new ArrayList<>();

        // add 1st & 2nd values of fibonacci sequence
        tb.add(0);
        tb.add(1);

        for (int i = 2; i <= n - 1; i++) {
            int n1 = tb.get(n - 1);
            int n2 = tb.get(n - 2);

            tb.add(n1 + n2);
        }

        return tb.get(n - 1);
    }
}
