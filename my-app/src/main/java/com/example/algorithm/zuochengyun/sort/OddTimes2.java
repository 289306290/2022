package com.example.algorithm.zuochengyun.sort;

/**
 * 找出数组中只出现一次的两个数
 */
public class OddTimes2 {
    public static void main(String[] args) {
        //异或相关,一个数组中,只有两个个数出现了奇数次,其他的都是出现偶数次,找到出现奇数次的这两个数
        int arr[] = new int[]{2, 3, 4, 5, 3, 4, 2, 9};
//        int arr[] = new int[]{ 4, 5, 3, 4, 5, 13};
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            r = r ^ arr[i];
        }
        System.out.println("r="+r);
        System.out.println("r="+Integer.toBinaryString(r));
        int lastBitIsOne = r & (~r + 1);//一个数与上(他自己按位取反再+1),结果就是这个数的二进制中,最后一位是1的这个数
        System.out.println("lastBitIsOne="+lastBitIsOne + ",2进制是:"+Integer.toBinaryString(lastBitIsOne));
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & lastBitIsOne) == 0) {
                one = one ^ arr[i];
            }
        }
        System.out.println("数组中只出现一次的两个数是:其中一个是"+one+",两一个数是:" + (r ^ one));
    }


}
