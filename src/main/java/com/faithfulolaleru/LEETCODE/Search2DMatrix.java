package com.faithfulolaleru.LEETCODE;

public class Search2DMatrix {

    // 74. Search a 2D Matrix
    // chatGPT solution
    public boolean searchMatrix(int[][] matrix, int target) {

        // matrix is ordered ascending already, so just flatten
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;   // or (right + left) / 2
            int midValue = matrix[mid / cols][mid % cols];   // don't really get this

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;  // use right half
            } else {
                right = mid - 1;   // use left half
            }
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {

        // matrix is ordered ascending already, so just flatten
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            // int midValue = matrix[mid / cols][mid % cols];   // don't really get this

            int j = mid % cols;
            int i = mid / cols;  // (mid - j) / cols     // also works

            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] > target) right = mid - 1;  // use left half
            else left = mid + 1;  // use right half
        }

        return false;
    }
}
