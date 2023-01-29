package com.douye.leetcode;

public class leetcode912_排序数组 {

    public int[] sortArray(int[] nums) {
        quick(nums, 0, nums.length - 1);
        return nums;
    }

    public void quick(int[] nums, int l, int r) {
        if (l > r) return;
        int partition = partition(nums, l, r);
        quick(nums, l, partition - 1);
        quick(nums, partition + 1, r);
    }

    public int partition(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int temp = nums[l];
        while (l <= r) {
            while (l<=r && nums[l]<temp) l++;
            while (l<=r && nums[r]>temp) r--;
            if (l>=r) break;
            swap(nums, l, r);
            l++;
            r--;
        }
        swap(nums, left, r);
        return r;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
