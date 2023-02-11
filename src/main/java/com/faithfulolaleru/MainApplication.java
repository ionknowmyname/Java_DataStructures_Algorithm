package com.faithfulolaleru;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainApplication {

    public static void main(String[] args) {
        MainApplication main = new MainApplication();
        //int rec = main.factorial(4);
        // no need for factorial method to be static if we calling it thru main

        // int rec = factorial(4);
        // int fib = fibonacci(4);
        // int add = addNoOfDigitsTogether(547); // 7 + 4 + 5 + 0
        // int power = powerOf(3, 3);
        // int gcd = greatestCommonDivisor(8,4);
        // int decimal  = decimalToBinary(8);
        // int[] TwoSum = main.twoSum(new int[]{2, 7, 11, 15}, 9);
        String maxProduct = main.maxProductOfTwoInt(new int[]{2, 7, 11, 15});

        // System.out.println(Arrays.toString(TwoSum));
        System.out.println(maxProduct);

    }

    public int factorial(int n){

        if(n < 1) return -1;
        if(n == 0 || n == 1){
            return 1;
        }

        System.out.println(n);
        return n * factorial(n-1);
    }

    public static int fibonacci(int n){

        if(n < 0) return -1;
        if(n == 0 || n == 1){
            return n;  // fibonacci of 0 is 0, and of 1 is 1
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    // memoization
    public static int fibonacciDynamicProgramming(int n, int[] memo) {
        if(memo[n] == 0) {  // if we've found fibonacci of a number, return it without entering if-block
            if(n < 2) {
                memo[n] = n;
            } else {
                int left = fibonacciDynamicProgramming(n - 1, memo);
                int right = fibonacciDynamicProgramming(n - 2, memo);
                memo[n] = left + right;
            }
        }

        return  memo[n];
    }

    public static int addNoOfDigitsTogether(int n){
        // on javascript, I split the digit of the number and added to an array
        // then used a for-loop to add up all the digits together

        if(n == 0 || n < 0) return 0; // no -ve nos. allowed

        return n%10 + addNoOfDigitsTogether(n/10);


        // OR


        /*int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;*/
    }

    public static int powerOf(int base, int exp){
        // X^n = X^1 * X^(n-1)
                // X^1 = n

        if(exp < 0) return -1;
        if(exp == 0) return 1;

        return base * powerOf(base, exp-1);
    }

    public static int greatestCommonDivisor(int a, int b){

        if(a < 0 || b < 0) return -1;
        if(b == 0) return a;

        return greatestCommonDivisor(b, a%b);  // euclidean algorithm
    }

    public static int decimalToBinary(int n){
        if(n == 0) return 0;

        return n%2 + 10*decimalToBinary(n/2);
    }

    public int[] twoSum(int[] nums, int target){
        // return the index of two numbers that when you add them, they
        // equal to the target number

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No solution found");
    }

    public String maxProductOfTwoInt(int[] nums){
        int maxProduct = 1;
        String ans = "";
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] * nums[j] > maxProduct) {
                    maxProduct = nums[i] * nums[j];
                    ans = nums[i] + "," + Integer.toString(nums[j]) + ", and maxProduct is = "
                            + Integer.toString(maxProduct);
                }
            }
        }
        return ans;
    }

    public boolean isUnique(int[] nums){
        // check that all the numbers in array are unique

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return false;
                }
            }
        }
        return true;
    }

}

