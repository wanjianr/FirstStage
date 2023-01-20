package com.douye.leetcode;

public class leetcode53_最大子数组和 {

    public static void main(String[] args) {
        int[] req = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        int[] req1 = new int[] {-2, -1};
        int i = maxSubArray(req);
        System.out.println(i);
    }

    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        int len = nums.length;
        for(int left =0; left<len; left++) {
            if(sum > 0) {
                sum = sum + nums[left];
            } else {
                sum = nums[left];
            }
            res = Math.max(res, sum);
        }
        return res;

    }
}
