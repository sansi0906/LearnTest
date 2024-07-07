package com.sansi.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupOnClass1 {
    public void stu1(){
        System.out.println("Class1中的 stu1");
    }

    public void stu2(){
        System.out.println("Class1中的 stu2");
    }
}
