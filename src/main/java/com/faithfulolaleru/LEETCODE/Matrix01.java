package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class Matrix01 {

    private static final int MAX_DISTANCE = 10000;   // from question
    // 542. 01 Matrix
    public int[][] updateMatrix(int[][] mat) {

        int m = mat.length, n = mat[0].length;
        int[][] distance = new int[m][n];

        // instantiate the distance array with max value, then keep reducing when you see a lesser value
        for (int r = 0; r < m; r++) {
            Arrays.fill(distance[r], MAX_DISTANCE);
        }

        // for updating top & left closest distances array, bt still traverse whole matrix array
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if(mat[r][c] == 0) {  // the closest 0 is itself so dist to closest 0 is 0
                    distance[r][c] = 0;
                    continue;
                }

                if(r > 0) {
                    // we can check closest 0 upwards, then pick the closest one
                    // the +1 is coz the dist between the cell above and the current cell (below) is gonna be +1
                    // coz adjacent cells are +1 of each other

                    distance[r][c] = Math.min(distance[r][c], distance[r - 1][c] + 1);

                    // since nested for-loop is taking us from top left to down right, once row > 0, all the distances
                    // on 0 row would have been set from the MAX_DISTANCE to the correct actual distance
                }

                if(c > 0) {
                    // we can check closest 0 leftwards, then pick the closest one
                    // the +1 is coz the dist between the cell to the left and the current cell (right of left) is gonna be +1
                    // coz adjacent cells are +1 of each other

                    distance[r][c] = Math.min(distance[r][c], distance[r][c - 1] + 1);
                }
            }
        }

        // for updating right & bottom closest distance array bt also traverses whole matrix array
        for (int r = m - 1; r >= 0; r--) {   // start from last row to first row
            for (int c = n - 1; c >= 0; c--) {   // start from last column to 1st column

                // no need to set distance[r][c] to 0 when matrix value at (r,c) is 0 coz it was set in first
                // traversal of matrix

                if(r != m - 1) {
                    // if r is not last row, we can check closest 0 between current and row below (last row)

                    distance[r][c] = Math.min(distance[r][c], distance[r + 1][c] + 1);
                }

                if(c != n - 1) {
                    // we can check closest 0 rightwards, then pick the closest one

                    distance[r][c] = Math.min(distance[r][c], distance[r][c + 1] + 1);
                }
            }
        }

        return distance;
    }
}
