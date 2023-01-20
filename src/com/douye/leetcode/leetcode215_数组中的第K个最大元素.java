package com.douye.leetcode;

import java.util.Random;

public class leetcode215_数组中的第K个最大元素 {
    public static void main(String[] args) {
        int[] arr = new int[] {3,2,3,1,2,4,5,5,6};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public void maopaoSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length - i; j++) {
                boolean swap = true;
                if (nums[j] < nums[j-1]) {
                    swap(nums, j ,j-1);
                    swap = false;
                }
                if (swap) {
                    break;
                }
            }
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public static void chooseSort(int[] nums) {
        if (nums.length < 1) return;
        for (int i = 0; i < nums.length; i++) {
            int max = i;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] > nums[max]) {
                    max = j;
                }
            }
            if (max != i) {
                swap(nums, max, i);
            }
        }
    }

    /**
     * 插入排序
     * @param nums
     */
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            // 需要往[0, j-1]中插入第j个元素
            int temp = nums[j];
            // 当[0, j-1]中的元素比temp大时，则整体向右移动一位
            while (j > 0 && temp < nums[j-1]) {
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = temp;
        }
    }

    /**
     * 快速排序
     * @param nums
     */
    public static void quickSort(int[] nums) {
        quick(nums, 0, nums.length-1, 10000);
    }

    public static void quick(int[] nums, int l, int r, int k) {
        if (l >= r) return;
        // 以头节点为基准，对数组进行排序
        int p = quickMethod(nums, l, r);
        quick(nums, l, p-1, k);
        quick(nums, p+1, r, k);
    }

    public static int quickMethod(int[] nums, int l, int r) {
        Random random = new Random();
        int i = l + random.nextInt(r - l + 1) ;
        swap(nums, l, i);
        int priot = nums[l];
        while (l < r) {
            // 下面两个while中必须有一个等于，且仅一个，否则会出现死循环
            while (l < r && nums[l] > priot) l++;
            while (l < r && nums[r] <= priot) r--;
            if (l < r) {
                swap(nums, l, r);
            }
        }
        nums[l] = priot;
        return l;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid+1, r);
            merge(nums, l, mid, r);
        }
    }

    public static void merge(int[] nums, int l, int m, int r) {
        int[] temp = new int[nums.length];
        // 备份
        for (int i = l; i <= r; i++) {
            temp[i] = nums[i];
        }
        int start = l;
        int mid = m+1;
        for (int i = l; i <= r; i++) {
            if (start > m) {
                nums[i] = temp[mid++];
            } else if (mid > r) {
                nums[i] = temp[start++];
            } else if (temp[start] <= temp[mid]) {
                nums[i] = temp[start++];
            } else {
                nums[i] = temp[mid++];
            }
        }
    }
}
