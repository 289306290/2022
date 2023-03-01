package com.example.algorithm.zuochengyun.sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = DuiShuQi.generateLengthRandomArr(10, 20);
        ArrUtils.printf(arr);
        quickSort(arr);
        ArrUtils.printf(arr);
    }

    private static void quickSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            ArrUtils.swap(arr, l + (int) ((r - l + 1) * Math.random()), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l -1; //左边界
        int more = r; //右边界
        while (l < more) { //l代表当前值, more代表右边界,
            //arr[r]代表数组最右面的基准值,最后才移动基准值,
            if (arr[l] < arr[r]) {  //当前数比基准值小,交换当前值l和左边界less后面挨着的值,并且当前值往后移(l++),左边界less也后移++less
                ArrUtils.swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) { //当前值比基准值大,交换当前值l和右边界more前面挨着的一个值,并且右边界往前移--more
                ArrUtils.swap(arr, --more, l);
            } else {
                l++;
            }
        }
        ArrUtils.swap(arr,more,r);
        return new int[]{less + 1, more};
    }


}
