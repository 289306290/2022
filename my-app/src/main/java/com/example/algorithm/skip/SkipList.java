package com.example.algorithm.skip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 实现跳跃表
 */
public class SkipList<T> {
    public static void main(String[] args) {
        SkipList<String> list = new SkipList<>();
        list.put(1, "1");
        list.put(2, "2");
        list.put(3, "3");
        list.put(5, "5");
        list.put(100, "100");
        list.put(66, "66");

        System.out.println(list);
        System.out.println("查找66 : " + list.get(66));
        list.delete(66);
    }

    /**
     * 跳跃表的节点的构成
     *
     * @param <E>
     */
    private static class SkipNode<E> {
        /**
         * 存储的数据
         */
        E val;
        /**
         * 跳跃表按照这个分数值进行从小到大排序。
         */
        Integer score;
        /**
         * next指针，指向下一层的指针
         */
        SkipNode<E> next, down;

        SkipNode(E val, int score) {
            this.val = val;
            this.score = score;
        }
    }
    /** 节点的高度，这里限制最高 4 层 */
    private static final int MAX_LEVEL = 4;

    /**
     * 跳跃表数据结构
     */
    private SkipNode<T> head;
    /** 节点级别 */
    private int level = 0;
    /**
     * 用于产生随机数的Random对象
     **/
    private Random random = new Random();

    public SkipList() {
        //创建默认初始高度的跳跃表
        this(4);
    }

    /**
     * 跳跃表的初始化
     */
    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp = null;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<T>(null, Integer.MAX_VALUE);
            temp.down = prev;
            prev = temp;
        }
        //头节点
        head = temp;
    }

    /**
     * 产生节点的高度。使用抛硬币
     *
     * @return
     */
    private int getRandomLevel() {
        int lev = 1;
        while (random.nextInt() % 2 == 0) {
            lev++;
        }
        return lev > MAX_LEVEL ? MAX_LEVEL : lev;
    }

    /**
     * 查找跳跃表中的一个值
     *
     * @param score 分数
     * @return
     */
    public T get(Integer score) {
        SkipNode<T> t = head;
        while (t != null) {
            if (t.score.equals(score)) {
                return t.val;
            }
            if (t.next == null) {
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    return null;
                }
            }
            if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
        return null;
    }

    /**
     *
     * @param score 分数键（排序）
     * @param val  值
     */
    public void put(Integer score, T val) {

        SkipNode<T> t = head, cur = null;
        //记录每一层当前节点的前驱节点
        List<SkipNode<T>> path = new ArrayList<>();
        while (t != null) {
            if (t.score .equals(score)) {
                //表示存在该值的点，表示需要更新该节点
                cur = t;
                break;
            }
            if (t.next == null) {
                //需要向下查找，先记录该节点
                path.add(t);
                if (t.down != null) {
                    //进入下一个层级
                    t = t.down;
                    continue;
                } else {
                    break;
                }
            }
            if (t.next.score > score) {
                //需要向下查找，先记录该节点
                path.add(t);
                if (t.down == null) {
                    break;
                }
                t = t.down;
            } else {
                t = t.next;
            }
        }
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else {
            //随机层级
            int lev = getRandomLevel();

            if (lev > level) {
                SkipNode<T> temp = null;
                //前驱节点现在是top了
                SkipNode<T> prev = head;
                while (level++ != lev) {
                    temp = new SkipNode<T>(null, Integer.MIN_VALUE);
                    //加到path的首部
                    path.add(0, temp);
                    temp.down = prev;
                    prev = temp;
                }
                //头节点
                head = temp;
                //level长度增加到新的长度
                level = lev;
            }
            //从后向前遍历path中的每一个节点，在其后面增加一个新的节点
            SkipNode<T> downTemp = null, temp, prev;
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    /**
     * 根据score的值来删除节点。
     *
     * @param score 分数键（排序）
     */
    public void delete(Integer score) {
        //1,查找到节点列的第一个节点的前驱
        SkipNode<T> t = head;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
                continue;
            }
            // 在这里说明找到了该删除的节点
            if (t.next.score.equals(score)) {
                t.next = t.next.next;
                t = t.down;
                //删除当前节点后，还需要继续查找之后需要删除的节点
                continue;
            }

            if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
    }

    /**
     * 输出结果，一层一层输出
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<T> t = head, next;
        while (t != null) {
            next = t;
            while (next != null) {
                sb.append(next.score).append(" ");
                next = next.next;
            }
            sb.append("\n");
            t = t.down;
        }
        return sb.toString().replace(Integer.MAX_VALUE+"","").replace("-2147483648","");
    }
}
