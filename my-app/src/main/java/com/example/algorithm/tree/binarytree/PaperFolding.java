package com.example.algorithm.tree.binarytree;

/**
 * 将一张细纸条,正面永安朝向自己.
 * 上下对折,打开会发现中间有一条凹线.
 * 如果对折之后再对折，打开会发现，在上一次对折出现的线上下分别会出现两条线,上方是凹线，下方是凸线。
 * 问 对折N次后，打开纸条，从上到下打印这些折痕。是凹还是凸
 *
 * 其实就是棵二叉树, 根节点是凹，左子树都是凹，右子树都是凸
 */
public class PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    //递归过程,来到了某一个节点
    //i是节点的层数，N一共的层数,down == true 凹  down ==false 凸
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
