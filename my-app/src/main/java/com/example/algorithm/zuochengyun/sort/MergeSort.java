package com.example.algorithm.zuochengyun.sort;


public class MergeSort {
    public static void main(String[] args) {
        int[] arr = DuiShuQi.generateLengthRandomArr(10, 100);
        System.out.println("原数组:");
        ArrUtils.printf(arr);
        mergeSort(arr);
        System.out.println("排序完成");
        ArrUtils.printf(arr);
    }

    public static void mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length-1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1=l, p2 = mid +1, i =0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int m = 0; m < help.length; m++) {
            arr[l+m] = help[m];
        }
    }
}
