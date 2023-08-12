package com.faithfulolaleru.DivideAndConquer;

public class ZeroOneKnapsackProblem {

    /*
    *   Given weights & profits of N items, find the max profit within a given capacity C
    *   Items cannot be broken/split
    *
    *   This is like fractional knapsack problem, except we can break the items like in fractional
    *    knapsack
    *
    * */


    public static void main(String[] args) {

        int[] profits = { 31, 26, 17, 72 };
        int[] weights = { 3, 1, 2, 5 };

        System.out.println(knapSack(profits, weights, 7));
    }


    private static int knapSack(int[] profits, int[] weights, int capacity, int currentIndex) {

        // base conditions
        if(capacity <= 0 || currentIndex < 0 || currentIndex >= profits.length) return 0;

        int profit1 = 0, profit2 = 0;
        if(weights[currentIndex] <= capacity) {
            // takes first item
            profit1 = profits[currentIndex] + knapSack(profits, weights, capacity - weights[currentIndex], currentIndex + 1);

            // don't take first item
            profit2 = knapSack(profits, weights, capacity, currentIndex + 1);
        }

        return Math.max(profit1, profit2);
    }

    public static int knapSack(int[] profits, int[] weights, int capacity) {
        return knapSack(profits, weights, capacity, 0);
    }
}
