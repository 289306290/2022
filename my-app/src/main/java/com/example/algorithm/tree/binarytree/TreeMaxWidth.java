package com.example.algorithm.tree.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//如何完成二叉树的宽度优先遍历(常见题目:求一棵二叉树的宽度)
public class TreeMaxWidth {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //宽度优先遍历二叉树
    public static void w(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (null != cur.left) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    //求一棵二叉树的最大宽度
    public static int weight(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
            if (null != cur.left) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        return max;
    }

}
