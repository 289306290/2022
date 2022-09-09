package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 一位数组中,只有两个数出现了一次，其他的都是出现两次，找到出现一次的这两个数分别是多少
 * 综合运用到了几个知识：
 *
 * 异或运算
 * 相同的数在某位上一定相同
 * 朴素的分治，将数分为两类，再分别求解
 * 移位运算
 * 与运算：判断某位是否为0这也可以出个题了
 *
 */
public class TowNumOdd {
    public static void main(String[] args) {
        int [] arr={12,21,3,3,4,4,5,6,5,6};
        //先求总的
        int n=0;
        int temp=0;
        for (int i = 0; i <arr.length ; i++) {
            temp^=arr[i];
        }

        while (true){
            if ((temp&(1<<n++))==1){
                break;
            }
        }
       /*这个得出N位为整数的第一个非0（即1）位的方法很巧妙：和1做按位与运算，如果位上为1结果为1，N求出；

        如果位上为0则结果为0，将1移动到下一位继续判断*/

        // 循环最后多加了1
        n--;

        List<Integer> num1=new ArrayList();
        List<Integer> num2=new ArrayList();

        for (int i = 0; i <arr.length ; i++) {
            if ((arr[i]&(1<<n))==0) {
                num1.add(arr[i]);
            }else {
                num2.add(arr[i]);
            }
        }
        int arr1=0,arr2=0;
        for (int i = 0; i <num1.size() ; i++) {
            arr1^=num1.get(i);

        }
        for (int i = 0; i <num2.size() ; i++) {
            arr2^=num2.get(i);

        }
        System.out.println(arr1+"+"+arr2);

    }
}