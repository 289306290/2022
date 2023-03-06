package com.example.algorithm.tree.binarytree;

import java.util.Stack;

/**
 * 二叉树遍历
 * 用递归和非递归两种方式实现二叉树的先序，中序，后序遍历
 * 如何直观的打印一颗二叉树
 *
 */
public class Traversal {

    static class Node<Integer>{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }

    }

    public static void preOrderRecur(Node head) {
        if (null == head) {
            return;
        }
        System.out.println(head.value);
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (null == head) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value);
        inOrderRecur(head.right);
    }

    public static void postOrderRecur(Node head) {
        if (null == head) {
            return;
        }
        postOrderRecur(head.left);
        System.out.println(head.value);
        postOrderRecur(head.right);
    }


    public static void preOrderUnRecur(Node head) {
        System.out.println("pre-order");
        if (null != head) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value);
                if (null != head.right) {
                    stack.push(head.right);
                }
                if (null != head.left) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void postOrderUnRecur(Node head) {
        System.out.println("post-order:");
        if (null != head) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value);
            }
        }
    }

    public static void inOrderUnRecur(Node head) {
        System.out.println("in-order:");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         *                    5
         *              3          8
         *           2     4     7    10
         *        1             6    9   11
         */
        Node<Integer> head = new Node<>(5);
        head.left = new Node<>(3);
        head.right = new Node<>(8);
        head.left.left = new Node<>(2);
        head.left.right = new Node<>(4);
        head.left.left.left = new Node<>(1);
        head.right.left = new Node<>(7);
        head.right.left.left = new Node<>(6);
        head.right.right = new Node<>(10);
        head.right.right.left = new Node<>(9);
        head.right.right.right = new Node<>(11);

        //recursive
        System.out.println("=====================recursive=================");
        System.out.println("pre-order");
        preOrderRecur(head);
        System.out.println();
        System.out.println("in-order:");
        inOrderRecur(head);
        System.out.println();
        System.out.println("post-order");
        postOrderRecur(head);

        //unrecursive
        preOrderUnRecur(head);

        postOrderUnRecur(head);

        inOrderUnRecur(head);
    }
}
