package com.example.algorithm.zuochengyun.sort.other;

import com.example.algorithm.zuochengyun.sort.ArrUtils;
import com.example.algorithm.zuochengyun.sort.DuiShuQi;

public class GetMaxRecursion {
    public static void main(String[] args) {
        int arr[] = DuiShuQi.generateLengthRandomArr(10, 9);
        ArrUtils.printf(arr);
        System.out.println(getMax(arr,3,6));
    }


    /**
     * 局部最大值,递归解法
     * 求数组arr中,l位置到r位置的最大值
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int getMax(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int leftMax = getMax(arr, l, mid);
        int rightMax = getMax(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }
}
