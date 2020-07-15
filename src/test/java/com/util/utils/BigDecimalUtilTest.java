package com.util.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigDecimalUtilTest {

    @Test
    public void subtract() {
        System.out.println(BigDecimalUtil.subtract(5.62,3.26)==2.36);
    }

    @Test
    public void subtractUp() {
        System.out.println(BigDecimalUtil.subtractUp(5.62,3.26)==3.0);
    }

    @Test
    public void subtractDown() {
        System.out.println(BigDecimalUtil.subtractDown(5.62,3.26)==2.0);
    }

    @Test
    public void add() {
        System.out.println(BigDecimalUtil.add(3.82,6.18)==10.0);
    }

    @Test
    public void multiply() {
        System.out.println(BigDecimalUtil.multiply(3.0,8.0)==24.0);
    }

    @Test
    public void divide() {
    }

    @Test
    public void roundUp() {
    }

    @Test
    public void testRoundUp() {
    }

    @Test
    public void roundDown() {
    }

    @Test
    public void testRoundDown() {
    }

    @Test
    public void roundHalfUp() {
    }

    @Test
    public void testRoundHalfUp() {
    }
}