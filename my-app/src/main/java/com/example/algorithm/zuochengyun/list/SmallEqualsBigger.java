package com.example.algorithm.zuochengyun.list;


/**
 * 在链表中给定一个值,pivot ;小于pivot放链表左边,等于pivot放中间,大于pivot放右边
 * 题目: 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 给定一个单链表的头结点head，节点的值类型是整型，再给定一个整数pivot.实现一个调整链表的函数,将链表调整为
 * 左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点，右部分都是值大于pivot的节点。
 */
public class SmallEqualsBigger {

    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // smal head 小于pivot区间的头结点
        Node sT = null; //small tail 小于pivot区间的尾结点
        Node eH = null; // equal head //等于pivot区间的头结点
        Node eT = null; //equal tail //等于Pivot区间的尾结点
        Node bH = null; //big head 大于pivot区间的头结点
        Node bT = null; // big tail 大于pivot区间的尾结点

        Node next = null; // save next node
        //every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        // small and equal reconnect
        if (sT != null) { //如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT: eT;//下一步,谁去连大于区域的头,谁就变成eT
        }
        //上面的if,不管跑了没有 et
        //all reconnect
        if (eT != null) { //如果小于区域和等于区域，不是都没有
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }
}
