package com.example.algorithm.zuochengyun.sort;

public class InsertSort {
    public static void main(String[] args) {
        int arr[] = new int[]{3, 5, 1, 8, 9, 6, 7, 2, 4};
        ArrUtils.printf(insertSort(arr));
    }

    public static int[] insertSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                ArrUtils.swap(arr,j,j+1);
            }
        }
        return arr;
    }
}
