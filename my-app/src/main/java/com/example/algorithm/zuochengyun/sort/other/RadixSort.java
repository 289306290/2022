package com.example.algorithm.zuochengyun.sort.other;

import com.example.algorithm.zuochengyun.sort.ArrUtils;
import com.example.algorithm.zuochengyun.sort.DuiShuQi;

/**
 * 基数排序是一种桶排序
 */
public class RadixSort {

    //only for no-negative value
    public static void radixSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    // arr[begin..end]排序
    public static void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;
        int i=0, j=0;
        //有多少个数准备多少个辅助空间
        int[] bucket = new int[r - l + 1];
        for (int d = 1; d <= digit; d++) { //有多少位就进出桶几次,按照个位先进桶,然后出桶;然后再十位进桶,出桶;再百位进桶出桶;
            //10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0,1,2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix]; //count[0..9]

            //下面for循环结束后,count里面保存的是个位上数字是j的元素有几个。
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d); //取arr[i]个位(d=1)，十位(d=2),百位(d=3)的数;j依次代表个位/十位/百位上面的数字是几
                count[j]++;
            }

            //下面这个for循环结束后,count里面保存的是个位上数字<=j的元素有几个。
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //将arr数组按照从右往左的顺序计算,如果d(个,什,百)位上面的数字是j,同时知道d位上数字是j的元素有count[j]个,那么当前这个数arr[i]应该在count[j]-1的位置。
            for (i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j]-1] = arr[i];
                count[j]--; //上面处理了一个数,那么d位上面是j的元素个数要减1
            }
            //下面是出桶操作
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    /**
     * 求x的从又往左(个什百千万...)上的数字:d为1表示取个位,d==2取10位...
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
//        int[] arr = DuiShuQi.generateLengthRandomArr(10, 1000);
        int [] arr = new int[]{12,22,32,42,52,62,72,82,92};
        ArrUtils.printf(arr);
        System.out.println("maxbits--"+maxbits(arr));
        radixSort(arr);
        ArrUtils.printf(arr);
    }

    /**
     * 数组中最大值是几位数
     * @param arr
     * @return
     */
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res ++;
            max /= 10;
        }
        return res;
    }
}
