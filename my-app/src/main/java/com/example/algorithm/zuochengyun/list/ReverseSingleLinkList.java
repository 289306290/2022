package com.example.algorithm.zuochengyun.list;


public class ReverseSingleLinkList {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);


        Node result = reverse(node1);
        while (null != result) {
            System.out.println(result.getValue());
            result = result.getNext();
        }
    }


    /**
     * 反转单链表
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (null == head || head.getNext() == null) {
            return head;
        }
        Node curr = head.getNext();
        head.setNext(null);
        while (null != curr) {
            Node hold = curr.getNext();
            curr.setNext(head);
            head = curr;
            curr = hold;

        }
        return head;
    }
}
