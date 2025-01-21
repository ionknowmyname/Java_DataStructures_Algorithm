package com.faithfulolaleru.ForUoPeople;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week4 {
    public static void main(String[] args) {
        // Sample data
        float[] spArray = {100.5f, 102.0f, 98.5f, 105.0f, 107.5f, 102.5f, 99.0f, 101.5f, 100.0f, 104.0f};
        List<Float> stockPricesList = new ArrayList<>();
        for (float price : spArray) {
            stockPricesList.add(price);
        }

        // Calculating average stock price
        float averagePrice = calculateAveragePrice(spArray);
        System.out.println("Average Stock Price: " + averagePrice);

        // Finding maximum stock price
        float maxPrice = findMaximumPrice(spArray);
        System.out.println("Maximum Stock Price: " + maxPrice);

        // Counting occurrences of a specific price
        float targetPrice = 102.0f;
        int occurrences = countOccurrences(spArray, targetPrice);
        System.out.println("Occurrences of " + targetPrice + ": " + occurrences);

        // Computing cumulative sum of stock prices
        List<Float> cumulativeSum = computeCumulativeSum(stockPricesList);
        System.out.println("Cumulative Sum of Stock Prices: " + cumulativeSum);
    }

    // Method to calculate average stock price
    public static float calculateAveragePrice(float[] stockPrices) {
        float sum = 0.0f;
        for (float price : stockPrices) {
            sum += price;
        }
        return sum / stockPrices.length;
    }

    // Method to find the maximum stock price
    public static float findMaximumPrice(float[] stockPrices) {
        float maxPrice = stockPrices[0];
        for (float price : stockPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }
        return maxPrice;
    }

    public static float findMaximumPrice2(float[] stockPrices) {
        // Arrays.sort(stockPrices, Collections.reverseOrder());
        Arrays.sort(stockPrices);

        // return stockPrices[0]
        return stockPrices[stockPrices.length - 1];
    }

    // Method to count occurrences of a specific price
    public static int countOccurrences(float[] stockPrices, float targetPrice) {
        int count = 0;
        for (float price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }
        return count;
    }

    // Method to compute the cumulative sum of stock prices
    public static List<Float> computeCumulativeSum(List<Float> stockPrices) {
        List<Float> cumulativeSum = new ArrayList<>();
        float sum = 0.0f;
        for (float price : stockPrices) {
            sum += price;
            cumulativeSum.add(sum);
        }
        return cumulativeSum;
    }
}
