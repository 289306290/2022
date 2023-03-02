package com.example.algorithm.tree;

import com.example.algorithm.zuochengyun.sort.ArrUtils;


import java.util.PriorityQueue;

public class SortArrayDistanceLessK {


    public static void main(String[] args) {
        int[] arr = new int[]{4,2,5,1,3,8,6,7,9};
        ArrUtils.printf(arr);
        sortedArrDistanceLessK(arr, 3);
        ArrUtils.printf(arr);
    }


    /**
     * 堆排序扩展题目
     * 已知一个几乎有序的数组,几乎有序是指,如果把数组排好顺序的话,每个元素移动的距离可以不超过k,并且k相对于数组来说比较小.
     * 请选择一个合适的排序算法针对这个数据进行排序。
     * @param arr
     * @param k
     */
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k);index++) {
            heap.add(arr[index]);
        }
        int i =0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void main1(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(8);
        heap.add(3);
        heap.add(4);
        heap.add(9);
        heap.add(2);
        heap.add(7);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
