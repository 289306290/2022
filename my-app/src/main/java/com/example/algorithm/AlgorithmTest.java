package com.example.algorithm;


import java.util.Arrays;

/**
 * Java算法
 */
public class AlgorithmTest {
    public static void main(String[] args) {

//        System.out.println(biSearch(new int[]{1,3,4,5,9}, 9));

//        printArr(bubbleSort(new int[]{51,32,6,7,5,33,9}));
//        printArr(insertSort(new int[]{51,32,6,7,5,33,9}));

//        printArr(quickSort(new int[]{51,32,6,7,5,33,9}, 0, 6));
//        printArr(quickSort(new int[]{3,7,9,8,2}, 0, 4));
//        printArr(shellSort(new int[]{3,7,9,8,2}));
//        printArr(shellSort(new int[]{33,7,92,8,2,66,21}));


        printArr(mergeSort(new int[]{33,7,92,8,2,66,21}));

    }


    /**
     * 二分查找，返回值为 -1 代表没找到， 否则返回索引位置
     * @param array
     * @param a
     * @return
     */
    public static int biSearch(int[] array, int a) {
        int low = 0;
        int height = array.length -1;
        int mid;
        while (low <= height) {
            // 注意每次比较之后,mid要重新计算
            mid = (low + height) / 2;
            if (array[mid] == a) {
                return mid;
            } else if (array[mid] < a) {
                low = mid + 1;
            } else {
                height = mid - 1;
            }
        }
        return  -1;
    }


    public static void printArr(int[] array) {
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i]+", ");

        }
        System.out.println("");
    }


    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        //相邻的两个元素,两两比较,大的后移,一趟之后,最大的排在最后
        boolean exchange = false; //判断是否发生交换,如果一趟两两比较,没有发生交换,说明已经排好序了

        //外层循环是比较的多少趟,2个元素比较一趟... N个元素比较 N-1趟
        for(int i=0; i< array.length  -1; i++) {
            //内层循环,每次都是从第一个元素开始,两两比较;每次选出一个最大的放到最后;
            exchange = false;
            for(int j=0;j < array.length-1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    exchange = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if (!exchange) {
                System.out.println("已经排好序了");
                return array;
            }
        }
        return  array;

    }


    /**
     * 插入排序,像整理扑克一样，已经插入的数据是排好序的，从第2个数开始插入(下标为1)。
     * 如果插入的值比前面一个值小,则让前面的值往后移，然后插入的位置往前移;直到比较到数组的第一个位置
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        if (null == array || array.length < 2) {
            //如果少于2个元素就不用排序了
            return array;
        }
//        一共需要循环数组长度-1次,然后从第二个数开始插入(也就是下标1)
        for(int i=1; i< array.length; i++) {
            //将要进行插入的数
            int insertVal = array[i];
            //将要插入的位置下标
            int insertIndex = i;
            //只要插入的位置存在,也就是>=0,并且插入的值比前一个值小,则把前一个值后移,然后插入的位置往前挪一个
            while (insertIndex > 0 && insertVal < array[insertIndex - 1]) {
                array[insertIndex] = array[insertIndex - 1];
                insertIndex-- ;
            }
            //在插入的位置,把插入的值放进去
            array[insertIndex] = insertVal;

        }
        return array;
    }


    /**
     * 快速排序，选择一个关键值作为基准值,比基准值小的都在左边序列,比基准值大的都在右边序列，一般选择第一个值为基准值
     * 一次循环:从后往前比较，用基准值和最后一个值比较，如果比基准值小则交换位置，否则继续比较下一个，知道找到第一个比基准值小的值进行交换。
     * 找到这个值之后，又开始从前往后比较，如果有比基准值大的交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的值才交换。
     * 直到从前往后的比较索引>从后往前的比较索引，结束第一次循环，此时，对于基准值来说，左右两边就是有序的了。
     * @param arr
     * @param low
     * @param hign
     * @return
     */
    public static int[] quickSort(int[] arr,int low, int hign) {
        //定义最低最高索引
        int start =low, end = hign;
        //设定数组第一个值为基准值
        int baseVal = arr[low];

        while (start < end) {
            //从后往前比较,直到找到比基准值小的值
            while (start < end && arr[end] > baseVal) {
                end--;
            }
            if (arr[end] < baseVal) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }

            //从前往后比较,直到找到比基准值大的值
            while (start < end && arr[start] < baseVal) {
                start++;
            }

            if (arr[start] > baseVal) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;

            }

        }
        //此时基准值左边的都比基准值小，基准值右边的都比基准值大， 然后进行下面的递归调用
        if (low > start) {
            quickSort(arr, low, start - 1);
        }
        if (end < hign) {
            quickSort(arr, end + 1, hign);
        }

        return arr;
    }


    /**
     * 希尔排序,和直接插入排序一个道理,只不过刚开始步长比较大，然后慢慢缩小步长进行直接插入排序,直到步长=1
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        int buchang = array.length/2;
        while (buchang >= 1) {
            shellInsertSort(array, buchang);
            buchang = buchang / 2;
        }
        return array;
    }

    public static int[] shellInsertSort(int[] array,int buchang) {
        for(int i=buchang; i < array.length; i++) {
            int insertVal = array[i];
            int insertIndex = i;
            while (insertIndex > 0 && insertIndex>= buchang && insertVal < array[insertIndex - buchang]) {
                array[insertIndex] = array[insertIndex - buchang];
                insertIndex = insertIndex - buchang;
            }
            array[insertIndex] = insertVal;
        }
        return array;
    }


    /**
     * 归并排序,讲数组分为两个，一直分下去,最后数组中就一个元素了,然后讲两个已排序数组进行合并
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array) {
        if (null == array) {
            return new int[0];
        }
        if (array.length == 1) {
            //如果只分解到一个元素则直接返回改数
            return  array;
        }
        //将数组分解左右两半
        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array,  array.length / 2,array.length);

        //嵌套调用,对两半分别进行排序
        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left,right);
    }


    /**
     * 讲两个有序的数组,合并到一个里面
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        if (left == null) {
            left = new int[0];
        }
        if (right == null) {
            right = new int[0];
        }
        int []result = new int[left.length + right.length];
        int leftIndex =0, rightIndex =0, resultIndex =0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex];
                leftIndex++;
            } else {
                result[resultIndex++] = right[rightIndex];
                rightIndex++;
            }
        }
        while (leftIndex < left.length) {
                result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
        return result;
    }



}
