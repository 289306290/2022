package com.example.algorithm.zuochengyun.doublelist;

import java.util.StringJoiner;

public class DoubleList<Z> {
    private static class Node<Z> {
        private Z data;
        Node<Z> next;
        Node<Z> prev;
    }

    private Node<Z> head;
    private Node<Z> tail;
    private int length = 0;
    DoubleList (){
        this.head = new Node<>();
        this.tail = new Node<>();

        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    public int size(){
        return length;
    }

    public boolean addFirst(Z data) {
        return insert(0, data);
    }

    public boolean addLast(Z data) {
        return insert(length, data);
    }

    public boolean removeFirst(){
        return remove(0);
    }

    public boolean removeLast() {
        return remove(length - 1);
    }

    public boolean remove(Z data) {
        return remove(indexOf(data));
    }

    public void reverse(){
        if (head.next == tail) {
            return;
        }
        Node<Z> rh = new Node<>();
        rh.next = tail;
        Node<Z> cur = head.next;
        Node<Z> cn;
        while (cur != tail) {
            cn = cur.next;
            cur.next = rh.next;
            rh.next.prev = cur;
            cur.prev = rh;
            rh.next = cur;
            cur = cn;
        }
        rh.next.prev = head;
        head.next = rh.next;
    }

    public Z get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        int pos = -1;
        Node<Z> h =head;
        while (h.next != tail) {
            h = h.next;
            pos ++;
            if (index == pos) {
                break;
            }
        }
        return h.data;
    }

    public int indexOf(Z data) {
        if (length == 0 || data == null) {
            return -1;
        }
        boolean found = false;
        int pos = -1;
        Node<Z> h = head;
        while (h.next != tail) {
            h = h.next;
            pos++;
            if (data.equals(h.data)) {
                found = true;
                break;
            }
        }
        return found ? pos : -1;
    }

    public int lastIndexOf(Z data) {
        if (length == 0 || data == null) {
            return -1;
        }
        int pos = length - 1;
        Node<Z> t = tail.prev;
        while (t != head) {
            if (data.equals(t.data)) {
                break;
            }
            t= t.prev;
            pos--;
        }
        return pos;
    }

    public boolean insert(int index, Z data) {
        if (index < 0 || index > length || data == null) {
            return false;
        }
        int pos = -1;
        Node<Z> h = head;
        while (h.next != tail) {
            if (index - 1 == pos) {
                break;
            }
            pos++;
            h=h.next;
        }

        Node<Z> originNext = h.next;
        Node<Z> item = new Node<>();
        item.data = data;
        item.next = originNext;
        item.prev = h;
        originNext.prev = item;
        h.next = item;
        length++;
        return true;
    }

    public boolean remove(int index) {
        if (length == 0 || index < 0 || index >= length) {
            return false;
        }
        int pos = -1;
        Node<Z> h = head;
        while (h.next != tail) {
            h = h.next;
            pos++;
            if (index == pos) {
                break;
            }
        }
        h.prev.next = h.next;
        h.next.prev = h.prev;
        length--;
        return true;
    }

    public void clear() {
        while (size() > 0) {
            removeFirst();
        }
    }

    private String format(boolean hasIndex) {
        StringBuilder sb = new StringBuilder("{");
        int pos = -1;
        Node<Z> h = head;
        while (h.next != tail) {
            h = h.next;
            pos++;
            if (pos == length) {
                continue;
            }
            if (hasIndex) {
                sb.append("[").append(pos).append("]-> ");
            }
            sb.append(h.data).append(", ");
        }
        int last = sb.lastIndexOf(", ");
        if (last != -1) {
            sb.delete(last, last + 2);
        }
        sb.append("}");
        return sb.toString().replaceAll(" {2}", "");
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",DoubleList.class.getSimpleName()+"[","]")
                .add("length=" + length)
                .add("data=" + format(false))
                .toString();
    }

}
