package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() {

        System.out.println(tableSizeFor(0));
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(5));

    }

    /**
     * Returns a power of two table size for the given desired capacity.
     * See Hackers Delight, sec 3.2
     */
    public int tableSizeFor(int c) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
