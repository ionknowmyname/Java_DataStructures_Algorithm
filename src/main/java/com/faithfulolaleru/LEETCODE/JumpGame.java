package com.faithfulolaleru.LEETCODE;

public class JumpGame {

    // 55. Jump Game


    public boolean canJump(int[] nums) {

        int goalIndex = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= goalIndex) {
                goalIndex = i;
            }
        }

        return goalIndex == 0;

        /*
        *  we start counting from backwards, the first loop would not change anything,
        *  we can start from length - 2 to skip the first iteration; but from the 2nd loop,
        *  goalIndex would be the last num in array, 'i' would now be the one before the last
        *  index, if the value of that index plus the index can get us to the goal index,
        *  which would always be the index immediately after, then it means we can get to the
        *  next index from our current index, so we shift our counters to the lef. If after
        *  everything, our goal index gets to 0, it means we can start from 0 index and get to
        *  the end of the array successfully
        *
        * */
    }

    // counting forward
    public boolean canJump2(int[] nums) {
        int reachable = 0;

        for (int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;

            reachable = Math.max(reachable, i + nums[i]);
        }

        return true;
    }
}
