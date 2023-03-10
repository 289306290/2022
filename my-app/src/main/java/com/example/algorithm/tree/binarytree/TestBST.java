package com.example.algorithm.tree.binarytree;


import java.util.LinkedList;

//二叉搜索树,任何一个节点，都比他的左节点大，任何一个节点都比他的右节点小
public class TestBST {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    //判断一棵树是否是二叉搜索树，中序遍历如果是升序则说明是二叉搜索树
    public static boolean isBST(Node head) {
        if (null == head) {
            return true;
        }
        boolean isLeftBST = isBST(head.left);
        if (!isLeftBST) {
            return false;
        }
        if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return isBST(head.right);
    }



    public static class ReturnData{
        public boolean isBst;
        public int min;
        public int max;

        public ReturnData(boolean is, int mi, int ma) {
            isBst = is;
            min=mi;
            max =ma;
        }
    }
    //另一种方式判断是否是二叉搜索树
    public static ReturnData process1(Node x) {
        if (x == null) {
            return null;
        }
        ReturnData leftData = process1(x.left);
        ReturnData rightData = process1(x.right);
        boolean isBst = true;
        int min = x.value;
        int max = x.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        if (leftData != null && (!leftData.isBst || leftData.max >=x.value)) {
            isBst = false;
        }
        if (rightData != null && (!rightData.isBst || x.value>= rightData.min)) {
            isBst = false;
        }


        return new ReturnData(isBst, min, max);
    }

    /**
     * 是否是完全二叉树
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        if (null == head) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        //是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) ||
                    (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }


    /**
     * 是否是平衡二叉树
     * @param head
     * @return
     */
    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }
    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true,0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) +1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced &&
                Math.abs(leftData.height - rightData.height) <2;
        return new ReturnType(isBalanced,height);
    }

    private static class ReturnType {
        private int height;
        private boolean isBalanced;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    //是否是满二叉树,主函数入口
    public static boolean isF(Node head) {
        if (null == head) {
            return true;
        }
        Info data = proc(head);
        return data.nodes == (1 << data.height - 1);
    }
    public static class Info{
        public int height; //高度
        public int nodes;//节点个数

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info proc(Node node) {
        if (null == node) {
            return new Info(0, 0);
        }
        Info leftData = proc(node.left);
        Info rightData = proc(node.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);
    }
}
