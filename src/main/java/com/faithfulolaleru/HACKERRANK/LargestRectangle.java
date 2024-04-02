package com.faithfulolaleru.HACKERRANK;

import java.util.List;

public class LargestRectangle {


    public static long largestRectangle(List<Integer> h) {
        long maxArea = 0;

        for (int i = 0; i < h.size(); i++) {
            long minHeight = h.get(i);

            for (int j = i; j < h.size(); j++) {
                // pick the shortest height that's common to the values you comparing
                minHeight = Math.min(minHeight, h.get(j));
                long area = minHeight * (j - i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public static long largestRectangle2(List<Integer> h) {
        long maxArea = 0;

        for (int i = 0; i < h.size(); i++) {
            long heights = h.get(i);
            int left = i, right = i;   // pointers

            // so long as the prev height is greater than the current height,
            // & we've not reached the beginning of the height list, move pointer leftward
            while (left - 1 >= 0 && h.get(left - 1) >= heights) left--;

            // so long as the height to the right is greater than current height,
            // & we've not reached the end og heights array, move pointer rightward
            while (right + 1 < h.size() && h.get(right + 1) >= heights) right++;

            maxArea = Math.max(maxArea, (right - left + 1) * heights);
        }

        return maxArea;
    }
}
