package com.example.algorithm.tree;

import com.example.algorithm.zuochengyun.sort.ArrUtils;
import com.example.algorithm.zuochengyun.sort.DuiShuQi;

/**
 * 这个里面是大根堆
 * 堆排序
 * 完全二叉树就是满二叉树可以从最后的节点连续往前少几个,但是所有节点都是连续的。
 * 堆结构就是用数组实现的完全二叉树结构
 * 完全二叉树中如果每颗子树的最大值都在顶部就是大根堆
 * 完全二叉树中如果每颗子树的最小值都在顶部就是小根堆
 * 优先级队列其实是堆结构
 */
public class HeapSort {
    public static void main(String[] args) {
//        int index = 0;
//        System.out.println((index-1)/2);
        int[] arr = DuiShuQi.generateLengthRandomArr(10, 100);
        ArrUtils.printf(arr);
        heapSort(arr);
        ArrUtils.printf(arr);
    }

    //大根堆
    public static void heapSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        /**
         * 这两个for循环,用任何一个都可以,
         *
         * 这一个是先将数组中的每个元素进行插入构建为大根堆
         *          for (int i = 0; i < arr.length; i++) { // O(N)
         *             heapInsert(arr, i);  //O(logN)
         *         }
         * 这一个是默认数组已经有了,从最后一个节点开始heapify操作,调整为大根堆。
         *         for (int i = arr.length - 1; i >= 0; i--) {
         *             heapify(arr,i,arr.length);
         *         }
         */

        /*for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i);  //O(logN)
        }*/

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr,i,arr.length); //O(logN)
        }

        int heapSize = arr.length;
        ArrUtils.swap(arr, 0, --heapSize);
        while (heapSize > 0) { //O(N)
            heapify(arr, 0, heapSize); //O(logN)
            ArrUtils.swap(arr, 0, --heapSize); //O(1)
        }
    }

    //某个数在index位置,能否往上移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { //大根堆,一个数index位置,和他的父节点(index-1)/2比较. 另外当index=0的时候到达跟节点,这时候(index-1)/2也是0,也不会满足while条件
            ArrUtils.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //某个数在index位置,能否往下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; //左孩子下标
        while (left < heapSize) { //下方还有孩子
            //两个孩子中,谁的值大,把下标给largestIndex
             int largestIndex = left +1 < heapSize && arr[left +1] > arr[left] ? left +1 : left;
             //父亲和较大孩子之间,谁的值大,把下标给largestIndex
            largestIndex = arr[largestIndex] > arr[index] ? largestIndex: index;
            if (largestIndex == index) {
                break; //如果当前节点父亲index比两个孩子中最大的值都大,break
            }
            ArrUtils.swap(arr, largestIndex, index);
            index = largestIndex;
            left = largestIndex * 2 + 1;
        }
    }

}
