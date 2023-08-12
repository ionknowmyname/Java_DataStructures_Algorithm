package com.faithfulolaleru.LEETCODE;

import java.util.HashMap;
import java.util.Map;

public class BuySellStock {


    // 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int totalMaxProfit = 0; int currentLeastPrice = Integer.MAX_VALUE; int currentMaxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < currentLeastPrice) {
                currentLeastPrice = prices[i];
            }

            currentMaxProfit = prices[i] - currentLeastPrice;

            if (totalMaxProfit < currentMaxProfit) {
                totalMaxProfit = currentMaxProfit;
            }
        }

        return totalMaxProfit;
    }

    public int maxProfit2(int[] prices) {
        int totalMaxProfit = 0; int currentLeastPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            currentLeastPrice = Math.min(currentLeastPrice, prices[i]);
            totalMaxProfit = Math.max(prices[i] - currentLeastPrice, totalMaxProfit);
        }

        return totalMaxProfit;
    }
}
