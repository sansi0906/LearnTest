package com.sansi.testng;

import org.testng.annotations.Test;

public class IngoreTest {
    @Test
    public void ingoreOne(){
        System.out.println("这个Ingore执行～");
    }

    @Test(enabled = false)
    public void ingoreTwo(){
        System.out.println("这个 ingore 不执行～");
    }

    @Test(enabled = true)
    public void ingoreThree(){
        System.out.println("这个 ingore 会忽略吗？");
    }
}
