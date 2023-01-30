package com.douye.leetcode;

public class leetcode704_二分查找 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return -1;
    }
}
