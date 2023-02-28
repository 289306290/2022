package com.example.algorithm.zuochengyun.sort;


public class SelectionSort {

    public static void main(String[] args) {
//        int arr[] = new int[]{3,9,8,4,6,7,2,5,1};
        int arr[] = new int[]{3,4,9,6,7,1,2,5};
        ArrUtils.printf(selectionSort(arr));
    }

    public static int[] selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                ArrUtils.swap(arr,i,minIndex);
            }
        }
        return arr;
    }


}
