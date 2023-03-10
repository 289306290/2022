package com.example.algorithm.tree.binarytree;

import java.util.HashMap;
import java.util.HashSet;

/**
 *给定两个二叉树节点node1和node2,找到他们的最低公共祖先节点
 */
public class LowestCommonAncestor {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // o1 o2一定属于head为头的树
    //返回o1和o2的最低公共祖先
    public static Node lowestCommonAncestor(Node head, Node o1, Node o2) {
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);//head的头节点设置为自己
        processFather(head,fatherMap);
        HashSet<Node> set1 = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);
        //接下来开始从o2节点一直往上找,如果在set1中找到了,那找到的就是最低公共祖先
        while (!set1.contains(o2)) {
            o2 = fatherMap.get(o2);
        }
        return  o2;
    }

    private static void processFather(Node head, HashMap<Node, Node> fatherMap) {
        if (null == head) {
            return;
        }
        if (null != head.left) {
            fatherMap.put(head.left, head);
        }
        if (null != head.right) {
            fatherMap.put(head.right, head);
        }
        processFather(head.left, fatherMap);
        processFather(head.right, fatherMap);
    }


    /**
     * 代码简单的另一种方式,但是理解有点难
     * o1和o2的结构就两种:
     * a. o1是o2的祖先,或者o2是o1的祖先
     * b. o1与o2不互为公共祖先,他们的公共祖先并不是o1和o2自身。
     * 以下可以分别套用这两种情况进行分析。
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left !=null ? left : right;
    }

}
