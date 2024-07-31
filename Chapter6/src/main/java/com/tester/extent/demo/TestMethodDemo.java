package com.tester.extent.demo;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodDemo {
    @Test
    public void testOne() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void testTwo() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testThree() {
        Assert.assertEquals("aaaa", "aaaa");
    }

    @Test
    public void logDemo() {
        Reporter.log("我要输出的日志～");
        throw new RuntimeException("我想抛个异常～");
    }
}
