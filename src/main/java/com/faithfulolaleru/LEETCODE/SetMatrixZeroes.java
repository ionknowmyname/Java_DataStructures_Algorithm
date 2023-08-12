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

    public void setZeroesBestSolution(int[][] matrix){

        /*
        *   use 1st row & 1st column of matrix instead of creating new row & column arrays like in
        *   better solution; bt that would mean 1st row & 1st column would not be part of the main
        *   matrix we would be traversing, we'd be traversing the sub-matrix without 1st row & 1st column;
        *   so to cover up for that, we'd check 1st row & 1st column separately at beginning,
        *   if any 0 in 1st row/column array, set the whole array value to 0 respectively
        *
        *
        * */

        int m = matrix.length, n = matrix[0].length;
        boolean isRow0 = false, isCol0 = false;

        for(int j = 0; j < n; j++) {
            if(matrix[0][j] == 0) isRow0 = true;

            // scan 1st row, if there's any 0, set isRow to true
        }

        for(int i = 0;i < m; i++) {
            if(matrix[i][0]==0) isCol0=true;

            // scan 1st column
        }

        // start traversing from smaller sub-matrix
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    // instead of setting array values to 0 like in better solution,
                    // set the matrix values in 1st row & 1st column to 0

                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // now start setting the smaller sub-matrix values to 0 for anywhere in
        // 1st column/1st row where we see 0
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++){
                if(matrix[0][j]==0 || matrix[i][0]==0) matrix[i][j]=0;
            }
        }

        // 0 found in 1st row, set all 1st row values to 0
        if(isRow0) {
            for(int j = 0; j < n; j++) matrix[0][j] = 0;
        }

        // 0 found in 1st column, set all 1st column values to 0
        if(isCol0) {
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }
}
