package com.faithfulolaleru.DivideAndConquer;

public class LastCellGivenCost {

    /*
     *   in 2D matrix, find the number of paths to get from (0,0) to (n-1, n-1),
     *   given a total cost that shouldn't be exceeded. Each cell has a cost
     *   Check last cell minimum cost
     *   If we doing recursive solution, we start from (n-1, n-1) to (0,0)
     *   If we doing iterative, then we can start from (0,0) to (n-1, n-1).
     *
     * */


    public static void main(String[] args) {
        int[][] array =
            {
                { 4, 7, 1, 6 },
                { 5, 7, 3, 9 },
                { 3, 2, 1, 2 },
                { 7, 1, 6, 3 }
            };

        int cost = 25;

        System.out.println(numberOfPaths(array, array.length - 1, array[0].length - 1, cost));
        // ans should be 2
    }

    public static int numberOfPaths(int array[][], int row, int col, int cost) {

        // base conditions
        if(cost < 0) return 0;

        if(row == 0 && col == 0) {
            return array[0][0] - cost == 0 ? 1 : 0;
            // coz cost is reducing in recursion, by the time we get to (0,0), if we still have enough
            // cost to match the cost at (0,0) then there's 1 way to get from where we are coming from
            // to (0,0)
        }

        if(row == 0) {
            // we are at 1st row, so we can only go left
            return numberOfPaths(array, 0, col - 1, cost - array[row][col]);
        }

        if(col == 0) {
            // we are at 1st col, so we can only go up
            return numberOfPaths(array, row - 1, 0, cost - array[row][col]);
        }

        // else we can either go left or up
        int noOfPathsFromPreviousRow = numberOfPaths(array, row - 1, col, cost - array[row][col]);
        int noOfPathsFromPreviousCol = numberOfPaths(array, row, col - 1, cost - array[row][col]);

        return noOfPathsFromPreviousRow + noOfPathsFromPreviousCol;
    }
}


