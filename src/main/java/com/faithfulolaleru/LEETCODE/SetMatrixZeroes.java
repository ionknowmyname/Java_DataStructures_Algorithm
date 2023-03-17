package com.faithfulolaleru.LEETCODE;

import java.util.Arrays;

public class SetMatrixZeroes {



    // 73. Set Matrix Zeroes
    public void setZeroesBruteForce(int[][] matrix) {  // brute force
        int r = matrix.length, c = matrix[0].length;
        boolean[][] zeros = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) zeros[i][j] = true;
                // traverse whole matrix, & set true to where there's 0
            }
        }

        // anywhere with true, replace row & column with zeros
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (zeros[i][j] == true) {
                    fillRowWithZeros(i, matrix);
                    fillColumnWithZeros(j, matrix);
                }
            }
        }
    }

    private void fillRowWithZeros(int row, int[][] m) {
        for (int i = 0; i < m[row].length; i++) {
            m[row][i] = 0;
        }
    }

    private void fillColumnWithZeros(int col, int[][] m) {
        for (int i = 0; i < m.length; i++) {
            m[i][col] = 0;
        }
    }

    public void setZeroesBetterSolution(int[][] matrix){

        /*
        *   create rows array & column array & initialize to 1; while traversing matrix,
        *   if (i,j) = 0, set rowsArray[i] = 0 & colsArray[j] = 0;
        *   go back & iterate matrix, for any (i,j), if rowsArray[i] or colsArray[j] is 0
        *   then update matrix[i][j] to 0.
        *
        * */

        int m = matrix.length, n = matrix[0].length;
        int rowsArray[]= new int[m];
        int colsArray[]= new int[n];

        Arrays.fill(rowsArray,1);
        Arrays.fill(colsArray,1);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    rowsArray[i] = 0;
                    colsArray[j] = 0;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rowsArray[i] == 0 || colsArray[j] == 0)
                    matrix[i][j] = 0;
            }
        }
    }
}
