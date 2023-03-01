package com.example.algorithm.zuochengyun.sort;

public class QuickSortDoublePoint {
    public static void main(String[] args) {
        int[] arr = DuiShuQi.generateLengthRandomArr(10, 100);
        ArrUtils.printf(arr);
        quickSort(arr);
        ArrUtils.printf(arr);
    }

    public static void quickSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 算法入口,将arr数组中,从l到r下标位置的数组排序
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int middleIndex = quickSortDoublePointSwap(arr, l, r);
        quickSort(arr, l, middleIndex - 1);
        quickSort(arr, middleIndex + 1, r);
    }

    /**
     * 此方法的目的是将arr数组中,以第一个元素baseValue为标准,分为两段,第一段的值小于等于baseValue,后一段的值大于baseValue,
     * 然后返回最后一个baseValue所在的位置。
     * 双边指针（交换法）
     * 思路：
     * 记录分界值 baseValue，创建左右指针（记录下标）。
     * （分界值选择方式有：首元素，随机选取，三数取中法）
     *
     * 首先从右向左找出比baseValue小的数据，
     * 然后从左向右找出比baseValue大的数据，
     * 左右指针数据交换，进入下次循环。
     *
     * 结束循环后将当前指针数据与分界值互换，
     * 返回当前指针下标（即分界值下标）
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int quickSortDoublePointSwap(int[] arr, int l, int r) {
        int leftPoint = l;
        int rightPoint = r;
        int baseValue = arr[l];
        while (leftPoint < rightPoint) {
            while (leftPoint < rightPoint && arr[rightPoint] > baseValue) {
                rightPoint--;
            }
            while (leftPoint < rightPoint && arr[leftPoint] <= baseValue) {
                leftPoint++;
            }
            if (leftPoint < rightPoint) {
                ArrUtils.swap(arr,leftPoint,rightPoint);
            }
        }
        arr[l] = arr[rightPoint];
        arr[rightPoint] = baseValue;
        return rightPoint;
    }
}
