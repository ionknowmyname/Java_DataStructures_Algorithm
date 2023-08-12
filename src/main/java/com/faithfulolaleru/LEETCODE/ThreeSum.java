package com.faithfulolaleru.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    /*
    *   return a list of all 3 number combinations where the sum of all 3 digits = 0
    * */


    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        int[] arr2 = {0,1,1};
        int[] arr3 = {0,0,0};

        System.out.println(threeSum(arr));
        System.out.println(threeSum(arr2));
        System.out.println(threeSum(arr3));
    }

    // 15. 3Sum
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> toReturn = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) { // coz we'd stop at 3rd to the last element
            if(i==0 || (i > 0 && nums[i] != nums[i-1])) {  // if prev num is same as current num, dont run,
                // coz we already ran for the prev num

                // so if i is 1 number, we use two pointer to find the other two numbers so the sum of
                // the number at i & those 2 nums would be = 0; so we'd start at the number next to i &
                // stop at the last number;

                int l = i + 1;
                int h = nums.length - 1;
                int sum= 0 - nums[i]; // a+b+x=0, a+b=-x; so sum is basically -ve of the 1st value,
                // so the other 2 values can add up to sum to equal 0;

                while(l < h) {
                    if(nums[l] + nums[h] == sum) {

                        toReturn.add(Arrays.asList(nums[i],nums[l],nums[h]));

                        // if two numbers next to each other are the same, skip to the next one,
                        // to avoid duplicates in the ans
                        while(l < h && nums[l] == nums[l + 1]) l++;
                        while(l < h && nums[h] == nums[h - 1]) h--;

                        l++;
                        h--;
                    } else if(nums[l] + nums[h] < sum) {  // we neet to go higher so the values at l+h can equal the -ve value of sum
                        l++;
                    } else {  // values at l + h is more than -ve sum, so reduce the high index to drop l + h sum
                        h--;
                    }
                }
            }
        }

        return toReturn;
    }
}
