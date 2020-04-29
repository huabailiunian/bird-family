package com.bird.demo;

import org.junit.Test;

public class BaseTest {

    @Test
    public void name() {
        System.out.println(tableSizeFor(17));
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        return (n < 0) ? 1 : (n >= (1<<30)) ? (1<<30) : n + 1;
    }
}
