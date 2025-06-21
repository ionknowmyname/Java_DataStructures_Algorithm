package com.faithfulolaleru.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {

    public static void main(String[] args) {
        System.out.println(tribonacciDP(5));  // 4
        System.out.println(tribonacciDP(6));  // 7
        System.out.println(tribonacciDP(7));  // 13
        System.out.println(tribonacciDP(8));  // 24
    }

    public static int tribonacciDP(int n) {
        return tribonacciDP(n, new HashMap<>());
    }

    public static int tribonacciDP(int n, Map<Integer, Integer> memo) {
        if(n == 0 || n == 1) return 0;
//        if(n == 0) return 0;
//        if(n == 1) return 0;
        if(n == 2) return 1;

        if(memo.containsKey(n)) return memo.get(n);

        int result = tribonacciDP(n - 1, memo) + tribonacciDP(n - 2, memo) + tribonacciDP(n - 3, memo);
        memo.put(n, result);
        return result;
    }
}
