package com.sansi.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    //testng 最基本的注解，用来表示是测试的一部分
    @Test
    public void testCaseOne(){
        System.out.println("Test！这是一个测试用例，编号1111～");
    }
    @Test
    public void testCaseTwo(){
        System.out.println("Test!这是一个测试用例，编号 2222～");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod!这是在测试方法之前运行的～");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod!这是在测试方法之后运行的～");
    }

    @BeforeClass
    public void beforeClassRun(){
        System.out.println("BeforeClass！这是在类运行之前运行的～");
    }

    @AfterClass
    public void afterClassRun(){
        System.out.println("AfterClass!这是在类运行之后运行的～");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite!测试套件～");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite!测试套件～");
    }
}
