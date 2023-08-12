package com.faithfulolaleru.LEETCODE;

public class FloodFill {



    // 733. Flood Fill

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color) return image;  // do nothing coz color to change to is color already there

        fill(image, sr, sc, image[sr][sc], color);

        return image;
    }

    public void fill(int[][] image, int sr, int sc, int oldColor, int newColor) {

        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != oldColor) {
            return;  // do nothing
        }

        image[sr][sc] = newColor;

        // call recursively for the 4 adjacent sides of image[sr][sc]
        fill(image, sr - 1, sc, oldColor, newColor); // up
        fill(image, sr + 1, sc, oldColor, newColor); // down
        fill(image, sr, sc - 1, oldColor, newColor);  // left
        fill(image, sr, sc + 1, oldColor, newColor);  // right
    }

}
