package com.sansi.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "techer")
public class GroupOnClass3 {

    public void tec1(){
        System.out.println("Class3中的 tec1");
    }

    public void tec2(){
        System.out.println("Class3中的 tec2");
    }
}
