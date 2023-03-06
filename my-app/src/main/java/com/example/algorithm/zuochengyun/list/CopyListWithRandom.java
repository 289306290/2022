package com.example.algorithm.zuochengyun.list;

import java.util.HashMap;

/**
 * 复制含有随机指针节点的链表
 * 题目: 一种特殊的单链表节点类描述如下
 *  class Node{
 *      int value;
 *      Node next;
 *      Node rand;
 *      Node(int val){
 *          value = val;
 *      }
 *  }
 *
 *  rand 指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，
 *  也可能指向null.给定一个由Node节点类型组成的无环单链表的头结点head,请实现
 *  一个函数完成这个链表的复制，并返回复制的新链表的头结点。
 *  要求: 时间复杂度O(N),额外空间复杂度O(1)
 */
public class CopyListWithRandom {

    static class Node{
        int value;
        Node next;
        Node rand;
        Node(int val) {
            value = val;
        }
    }

    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            //cur 老节点
            // map.get(cur)复制出来的新节点
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = null;
        Node next = null;
        //copy node and link to every node
        // 1 -> 2
        // 1-> 1' -> 2
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //set copy node rand
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand !=null ? cur.rand.next:null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // split
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
