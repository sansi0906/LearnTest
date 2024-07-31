package com.sansi.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private ResourceBundle bundle;
    String url;
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("host");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
        String uri = bundle.getString("uri_getCookies");
        String testUrl = this.url + uri;
        System.out.println(testUrl);
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        this.store = client.getCookieStore();
        List<Cookie> cookiesList = store.getCookies();
        for (Cookie cookie : cookiesList) {
            String key_name = cookie.getName();
            String key_value = cookie.getValue();
            System.out.println("Cookies的key:" + key_name + "和value:" + key_value);

        }


    }






    @Test(dependsOnMethods = {"getCookies"})
    public void testPostWithCookies() throws IOException {
        //获取要测试接口
        String uri = bundle.getString("uri_postwithcookies");
        //拼接请求地址
        String testUrl = this.url + uri;
        //声明一个 client 对象，用来就进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，post 方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject parm = new JSONObject();
        parm.put("name", "huhansan");
        parm.put("age", "18");
        //设置请求头信息,配置 header 入参
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象进行响应信息的存储
        String result;
        //设置 cookies 信息
        client.setCookieStore(this.store);
        //执行 post 方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("111" + result);
        //处理相应结果，判断是否符合预期
        JSONObject resultJson = new JSONObject(result);
        System.out.println("222" + resultJson);
        //获取返回值
        String resultOne = resultJson.getString("huhansan");
        String resultTwo = resultJson.getString("status");
        //具体的判断返回值是否符合预期
        Assert.assertEquals("success", resultOne);
        Assert.assertEquals("1", resultTwo);


    }

}
