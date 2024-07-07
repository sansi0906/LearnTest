package com.sansi.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite 运行啦");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite 运行啦");
    }
    @BeforeTest
    public void beforeClass(){
        System.out.println("Before Test！！！");
    }

    @AfterTest
    public void afterClass(){
        System.out.println("After Test!!!");
    }
}
