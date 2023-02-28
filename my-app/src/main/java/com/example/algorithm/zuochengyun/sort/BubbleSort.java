package com.example.algorithm.zuochengyun.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = new int[]{3,9,8,4,6,7,2,5,1};
//        int arr[] = new int[]{3,4,9,6,7,1,2,5};
        ArrUtils.printf(bubbleSort(arr));
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrUtils.swap(arr,j,j+1);
                }
            }
        }
        return arr;
    }
}
