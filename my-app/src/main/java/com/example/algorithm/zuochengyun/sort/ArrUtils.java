package com.example.algorithm.zuochengyun.sort;

import java.util.Arrays;
import java.util.Scanner;

public class ArrUtils {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printf(int[] arr) {
        Arrays.stream(arr).forEach(e->{System.out.print(e+",");});
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("请输入一个数:");
        int r = new Scanner(System.in).nextInt();
        System.out.println("这个数的二进制表示是:" + Integer.toBinaryString(r));
        int lastBitIsOne = r & (~r + 1);
        System.out.println("这个数的二进制表示中,最后一位是1的数是"+ lastBitIsOne);
    }
}
