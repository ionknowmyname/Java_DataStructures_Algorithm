package com.faithfulolaleru.DivideAndConquer;

public class LastCellMinimumCost {

    /*
    *   in 2D matrix, find minimum cost to get from (0,0) to (n-1, n-1). Each cell has a cost
    *   Similar to grid traveller, check Dynamic Programming
    *   If we doing recursive solution, we start from (n-1, n-1) to (0,0)
    *   If we doing iterative, then we can start from (0,0) to (n-1, n-1).
    *
    * */


    public static void main(String[] args) {
        int[][] array =
            {
                { 4, 7, 8, 6, 4 },
                { 6, 7, 3, 9, 2 },
                { 3, 8, 1, 2, 4 },
                { 7, 1, 7, 3, 7 },
                { 2, 9, 8, 9, 3 }
            };

        System.out.println(findMinCost(array, array.length - 1, array[0].length - 1));
        // ans should be 36
    }


    public static int findMinCost(int[][] cost, int row, int col) {

        // base conditions
        if(row == -1 || col == -1) return Integer.MAX_VALUE;   // we shouldn't ever get here

        if(row == 0 && col == 0) return cost[0][0];
        // this should base us out, start adding from this value when recursion finally hits (0,0)

        int minCost1 = findMinCost(cost, row, col - 1);  // moving left
        int minCost2 = findMinCost(cost, row - 1, col);  // moving up

        int minCost = Integer.min(minCost1, minCost2);

        // add minimum cost to the current cell's cost
        return minCost + cost[row][col];
    }
}
