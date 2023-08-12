package com.faithfulolaleru.LEETCODE;

public class ContainerWithMostWater {


    // 2 pointer solution
    // 11. Container With Most Water
    public int maxArea(int[] height) {

        int left = 0, right = height.length - 1;
        int totalMax = 0;

        while(left < right) {
            if(height[left] < height[right]) {   // use the shorter height
                totalMax = Math.max(totalMax, height[left] * (right - left));
                left += 1;
            } else  {
                totalMax = Math.max(totalMax, height[right] * (right - left));
                right -= 1;
            }
        }

        return totalMax;
    }

    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while(left < right) {
            int w = right - left;
            int h = Math.min(height[left], height[right]);
            int area = h * w;
            max = Math.max(max, area);

            if(height[left] < height[right]) left++;
            else if(height[left] > height[right]) right--;
            else {
                left++;
                right--;
            }
        }

        return max;
    }
}
