package com.example.algorithm.zuochengyun.sort;

import java.util.Arrays;

public class DuiShuQi {

    public static int[] generateRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1) * Math.random() - (int)(maxValue * Math.random()));
        }
        return arr;
    }

    /**
     * 生产随机定长数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateLengthRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[maxSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue+1) * Math.random() - (int)(maxValue * Math.random()));
        }
        return arr;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
    public static void main(String[] args) {
//        int testTime = 500000;
        int testTime = 50;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArr(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            ArrUtils.printf(arr1);
            ArrUtils.printf(arr2);
            InsertSort.insertSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "fucking");
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((null == arr1 && null != arr2) || (null == arr2 && null != arr1)) {
            return false;
        }
        if (null == arr1 && null == arr2) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] copyArray(int[] arr) {
        if (null == arr) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
}
