package com.faithfulolaleru.LEETCODE;

public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
    }

    // by finding minimum value first
    // 33. Search in Rotated Sorted Array
    public static int search(int[] nums, int target) {

        if(nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        // find smallest number i.e start of array
        while(left < right) {
            int middle = left + (right - left) / 2;
            if(nums[middle] > nums[right]) { // smaller values on the right, use right subarray
                left = middle + 1;
            } else {  // smaller values on the left, use left subarray
               right = middle;
            }
        }

        // after loop, int left would be the smallest array value
        int start = left;

        // reinitialize left & right again  // kinda redundant
        left = 0;
        right = nums.length - 1;

        if(target >= nums[start] && target <= nums[right]) {
            // use right half
            // keep in mind, start is not 0, its index of lowest value in array gotten from earlier 'left'
            // from start to right is sorted ascendingly

            left = start;
        } else {
            // use left half
            // here start is still lowest value in array
            // also from nums[0] to nums[start-1] is also sorted ascendingly
            right = start;
        }

        //next we just do regular binary search
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if(nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {  // right array
                left =  middle + 1;
            } else {  // left array
                right = middle - 1;
            }
        }

        return -1;
    }

    // use binary search straight up
    public int search2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int mid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {   // left sub array
                if (nums[start] <= target && nums[mid] >= target) {   // left subarray
                    end = mid - 1;
                } else {  // right subarray
                    start = mid + 1;
                }
            } else {  // right sub array
                if (nums[end] >= target && nums[mid] <= target) {  // right subarray
                    start = mid + 1;
                } else {    // left subarray
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search3(int[] nums, int target) {
        int ans = bs(nums, target, 0, nums.length - 1);

        return ans;
    }

    int bs(int[] nums, int t, int s, int e) {
        if(s>e) return -1;

        int mid = s + (e-s)/2;
        if(nums[mid]==t) return mid;
        // check if we are in left sorted array
        if(nums[mid]>=nums[s]){
            // check target is in left sorted array
            if(t>=nums[s] && t<=nums[mid]){
                return bs(nums, t, s, mid-1);
            }
            return bs(nums, t, mid+1, e);
        }
        else{
            if(t>=nums[mid] && t<=nums[e]){
                return bs(nums, t, mid+1, e);
            }
            return bs(nums, t, s, mid-1);
        }

    }
}
