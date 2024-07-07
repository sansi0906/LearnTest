package com.sansi.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {
    @Test(groups = "server")
    public void testOne(){
        System.out.println("服务端测试 用例 1");
    }
    @Test(groups = "server")
    public void testTwo(){
        System.out.println("服务端测试 用例2");

    }
    @Test(groups = "client")
    public void testThree(){
        System.out.println("客户端测试 用例 3");

    }
    @Test(groups = "client")
    public void testFoure(){
        System.out.println("客户端测试 用例 4");

    }

    @BeforeGroups("server")
    public void beforeServer(){
        System.out.println("服务端测试用例之前运行");
    }

    @AfterGroups("server")
    public void afterServer(){
        System.out.println("服务端测试用力之后运行");
    }

    @BeforeGroups("client")
    public void beforeClient(){
        System.out.println("客户端测试用例之前运行");
    }

    @AfterGroups("client")
    public void afterClient(){
        System.out.println("客户端测试用例之后运行");
    }
}
