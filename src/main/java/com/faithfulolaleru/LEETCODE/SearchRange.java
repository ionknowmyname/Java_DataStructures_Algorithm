package com.faithfulolaleru.LEETCODE;

public class SearchRange {


    //  34. Find First and Last Position of Element in Sorted Array

    public int[] searchRange(int[] nums, int target) {

        int start = 0, end = nums.length - 1;
        int first = -1, last = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                first = mid;
                last = mid;

                // keep going back till the first value that match target
                while (first > 0 && nums[first - 1] == target) {
                    first--;
                }

                // keep going forward till the last value that match target
                while (last < nums.length - 1 && nums[last + 1] == target) {
                    last++;
                }

                break;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return new int[]{first, last};
    }

    public int[] searchRange2(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] =- 1;
        ans[1] =- 1;
        int i = 0;

        for(i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                ans[0] = i;
                ans[1] = i;
                break;
            }
        }

        // start from where i stopped, coz we've matched a first value
        for(int j = i; j < nums.length - 1; j++) {
            // if the next value also matches i that matches target, update the last
            if(nums[j] == nums[j + 1]) {
                ans[1] = j + 1;
            } else {
                break;
            }
        }
        return ans;
    }

    public int[] searchRange3(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int first = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                first = mid;
                // if mid is the first, the last would always be to the right,
                // so setting our right before mid is just to end the while loop quicker,
                // since we know for sure that our last value won't be found here
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return first;
    }

    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int last = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                last = mid;
                // same here too, if mid is our last value, we done here, it remains to end
                // the while loop as fast as possible
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return last;
    }
}
