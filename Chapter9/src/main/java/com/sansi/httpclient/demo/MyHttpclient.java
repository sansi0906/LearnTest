package com.sansi.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.*;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class MyHttpclient {
    @Test
    public void test1() throws IOException {
        //用来存放请求结果
        String result;
        HttpGet get = new HttpGet("https://www.baidu.com");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

    }
}
