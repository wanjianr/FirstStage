package com.douye.leetcode;

public class leetcode33_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l<r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) return mid;
            if (nums[l] < nums[mid]) {
                // 左边有序
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid;
                } else {
                    l = mid;
                }
            } else {
                // 右边有序
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
        }
        return -1;
    }
}
