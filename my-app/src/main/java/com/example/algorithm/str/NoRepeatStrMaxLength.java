package com.example.algorithm.str;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
public class NoRepeatStrMaxLength {

//    https://blog.csdn.net/qq_67503717/article/details/126536928
    public static int lengthOfLongestSubstring(String s) {
        if(s.length()<=1){
            return s.length();
        }
        int left = 0;
        int max = 1;
        char n[] = s.toCharArray();
        for(int i =1;i<n.length;i++) {
            for(int j = left;j<i;j++) {
                if(n[i] == n[j]) {
                    left =j+1;
                    break;
                }
            }
            max = max > (i-left+1)? max: (i-left+1);
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "abcabdweqf";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
