package com.sysdesign;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MurMurHash {

    public static Long getMurMurHash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);
        long m = 0xc6a4a7935bd1e995L;
        int r = 47;
        long h = seed ^ (buf.remaining() * m);
        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();
            k *= m;
            k ^= k >>> r;
            k *=m;
            h ^= k;
            h *=m;
        }
        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first;
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }
        h ^= h >>> r;
        h *=m;
        h^= h >>>r;
        buf.order(byteOrder);
        return h;
    }

    public static void main(String[] args) {
        long res = MurMurHash.getMurMurHash("L2ludGVyZXN0L21zZy9zdHUvMw==");
        System.out.println(res);

        System.out.println(String.format("%x", 100));
        System.out.println(String.format("%016x", 100));
        System.out.println(String.format("%a", 100.0));
        System.out.println(Integer.toHexString(10));
        System.out.println(Integer.highestOneBit(10));
        System.out.println(Integer.lowestOneBit(10));
        testMostRightOne();

    }

    public static boolean mostRightOne(int a) {
        int t1 = Integer.lowestOneBit(a);
        int t2 = a & (~a +1);
        return t1 == t2;
    }

    public static boolean aa(int a) {
        return -a == ~a +1;
    }

    public static void testMostRightOne() {
        for (int i = 0; i < 100; i++) {
            if (!mostRightOne(i)) {
                System.out.println("错了---->" + i);
            }
            if (!aa(i)) {
                System.out.println("正数取反加1等于负数，不对吗--->" + i);
            }
        }
    }


}
