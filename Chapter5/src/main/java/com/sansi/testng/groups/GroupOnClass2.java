package com.sansi.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupOnClass2 {
    public void stu3(){
        System.out.println("Class2中的 stu3");
    }

    public void stu2(){
        System.out.println("Class2中的 stu4");
    }
}
