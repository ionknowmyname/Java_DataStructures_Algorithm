package com.faithfulolaleru.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {


    public static void main(String[] args) {
        // System.out.println(coinChange3(new int[] { 1, 2, 5 }, 11));
        // System.out.println(coinChange3(new int[] { 2 }, 3));
        System.out.println(coinChange3(new int[] { 1, 2, 5, 10, 20, 50, 10, 1000 }, 2035));
        // System.out.println(coinChange2(new int[] { 186,419,83,408 }, 6249));
    }

    // my solution
    // greedy algorithm
    // timed out
    // 322. Coin Change
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int biggestCoin; // = coins[coins.length - 1];
        List<Integer> result = new ArrayList<>();

        while(amount > 0) {
            for(int i = coins.length - 1; i >= 0; i--) {
                biggestCoin = coins[i];
                if(amount > biggestCoin) {
                    amount = amount - biggestCoin;
                    result.add(biggestCoin);
                }
            }
        }

        return result.size();
    }

    // online solution
    // greedy alogrithm
    // failed some tests, but very good solution too
    public static int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        List<Integer> result = new ArrayList<>();

        for(int i = coins.length - 1; i >= 0; i--) {
            while(amount >= coins[i]) {
                amount -= coins[i];
                result.add(coins[i]);
            }
        }

        if(amount > 0) return -1;
        else return result.size();
    }


    /*
    *   best greedy approach bt fails some tests
    *   greedy programming can never solve completely, use Dynamic Programming
    *
    *   this is coz, when you start from the biggest numbers, it keeps using the biggest numbers till it cant anymore
    *   then it goes to the next smaller, bt if at that point the remaining amount is too small for the available coin
    *   numbers, it'll return -1 E.g 21  & { 2, 3, 5 }, it'll keep taking 5, 4 times till its 20, and not find 1 to make
    *   it 21 and therefore coz amount doesn't come back to 0, it'll return -1; whereas it coulda taken 5, 3 times, and
    *   3, 2 times to make 21; only Dynamic Programming can do that;
    * */
    public static int coinChange3(int[] coins, int amount) {
        Arrays.sort(coins);
        int index = coins.length - 1;
        List<Integer> result = new ArrayList<>();

        while(true && index >= 0) {
            int coinValue = coins[index];
            index--;
            int maxAmount = (amount / coinValue ) * coinValue;

            if(maxAmount > 0) {
                result.add(amount / coinValue);
                amount -= maxAmount;
            }

            // if(amount == 0) break;
        }

        if(amount > 0) return -1;

        int toReturn = 0;
        for (int i = 0; i < result.size(); i++) {
            toReturn += result.get(i);
        }
        return toReturn;
    }

    // first dynamic programming approach
    /*
    *   EXPLANATION: we using bottom-up/tabulation type of DP, so no recursion
    *   use dp array, and initialize to a value greater than the amount we want to calculate for, in the dp array,
    *   for each currency leading up to the amount we want, we store the fewest number of coins to make up that amount
    *   in the dp. E.G if we want to calc fewest number of coins to make 8, dp[1] would be the minuimum amount of coins
    *   to make 1, on and on till we get to dp[8] which we would return as the min. no. of coins to make 8
    *
    *   https://www.youtube.com/watch?v=hxaTNNQmx4c
    * */
    public static int coinChange4(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                // start from the coin, don't do amounts smaller than the coin for each loop, coz the
                // coin would be too big to be subtracted from the smaller amount

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];

        // if dp[amount] > amount, it means dp[amount] was never updated throughout the whole iteration, meaning the
        // amount could not be made up from any combination of coins in coins array
    }

    // another DP, this time its coin for each amount
    // https://www.youtube.com/watch?v=1R0_7HqNaW0
    public static int coinChange5(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // for each amount, try all the coins, memoize the mimimum number of coins to make that amount
        for (int i = 0; i <= amount; i++) {  // for each amount leading up to the main amount
            for (int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {  // coin can be subtracted from the current amount

                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    // where dp[i] in Math.min is either the (amount+1), what we initialized it to, or min number of
                    // coins from the previous coin
                } else {  // coin is bigger than current amount, break out
                    break;
                }
            }
        }


        return dp[amount] > amount ? -1 : dp[amount];

        // if dp[amount] > amount, it means dp[amount] was never updated throughout the whole iteration, meaning the
        // amount could not be made up from any combination of coins in coins array
    }
}
