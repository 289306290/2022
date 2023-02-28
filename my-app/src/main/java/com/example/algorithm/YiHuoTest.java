package com.example.algorithm;

public class YiHuoTest {

    public static void main(String[] args) {
        printOddTimesNum(new int[]{2,3,4,4,7,3});
    }

    private static void printOddTimesNum(int[] arr) {
        int t = 0 ;
        for (int a : arr) {
            t ^= a;
        }
        int right = t & (~t +1); //找到二进制中最后侧位1的值

        int onlyOne =0;
        for (int a : arr) {
            if ((a & right) == 1) {
                onlyOne ^= a;
            }
        }
        System.out.println("其中一个值:"+ onlyOne + "另一个值:"+ (t^onlyOne));

    }
}
