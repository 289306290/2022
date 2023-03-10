package com.example.algorithm.tree.binarytree;

/**
 * 后继节点
 * 题目: 现有一种新的二叉树节点类型如下
 *  public class Node{
 *      public int value;
 *      public Node left;
 *      public Node right;
 *      public Node parent;
 *      public Node(int val){
 *          value = val;
 *      }
 *  }
 * 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
 * 假设有一棵Node类型的节点组成的二叉树，书中每个节点的parent指针都正确地指向了父节点,head的parent指针指向null.
 * 只给一个在二叉树中的某个节点node,请实现返回node的后继节点的函数
 * 在二叉树的中序遍历的序列中，node的下一个节点叫做node的后继节点
 *
 */
public class NextNode {
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            value = val;
        }
    }


    /**
     * 分析:
     * 1.当node 存在右节点时候, node的后继节点就是右树上的最左节点就是它的后继节点
     * 2. 当node 不存在右树时候: 则从node往上找，发现某个节点是其父亲的左孩子时候,则此节点就是它的后继节点
     * 注意: 树上最后一个节点是不存在后继节点的。
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { //无右子树
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                //当前节点是其父亲节点右孩子,直到两种情况while停止:
                // 1当我是我父亲的左孩子时候停止(这时候父亲就是我的后继节点).
                // 2我的父亲是空的时候停止(这种情况是node是整棵树最右的节点)
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (null == node) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
