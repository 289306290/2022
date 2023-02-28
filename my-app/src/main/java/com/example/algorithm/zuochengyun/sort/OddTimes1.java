package com.example.algorithm.zuochengyun.sort;

/**
 * 找出数组中只出现一次的数
 */
public class OddTimes1 {
    public static void main(String[] args) {
        //异或相关,一个数组中,只有一个数出现了奇数次,其他的都是出现偶数次,找到出现奇数次的这个数
        int arr[] = new int[]{2, 3, 4, 5, 3, 4, 2};
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            r = r ^ arr[i];
        }
        System.out.println("数组中只出现一次的数是:"+r);
    }
}
