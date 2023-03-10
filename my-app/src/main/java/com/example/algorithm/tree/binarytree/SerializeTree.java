package com.example.algorithm.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * 就是内存里的一棵树如何变成字符串形式，又如何从字符串形式变成内存里的树。
 * 比如: 将一棵树按照先序方式变为一个字符串的形式。然后也可以从先序的字符串形式还原回tree
 * 如何判断一棵二叉树是不是另一棵二叉树的子树
 */
public class SerializeTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    //以 head为头的树,请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return  res;
    }

    //反序列化
    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
}
