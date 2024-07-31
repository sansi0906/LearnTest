package com.sansi.cases;


import com.sansi.config.TestConfig;
import com.sansi.model.InterfaceName;
import com.sansi.model.LoginCase;
import com.sansi.untils.ConfigFile;
import com.sansi.untils.DatabaseUntil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue", description = "测试准备工作，获取 httpclient对象")
    public void beforeTest() {
        //获取 拼接好的接口地址
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        //创建一个 Httpclient 对象
        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }

    @Test(groups = "loginTrue", description = "case-用户登陆成功")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUntil.getSqlSession();
        //获取表loginCase中 id 为1的用户名密码信息
        LoginCase loginCase = session.selectOne("loginCase", 1);
        //获取查表得到的期望结果
        String expectResult = loginCase.getExpected();
        //将接口测试的实际结果转换为 String 类型
        String result = getResult(loginCase);
        //将查表获取的期望结果与接口测试的实际值对比，
        Assert.assertEquals(expectResult, result);
    }

    @Test(groups = "loginFalse", description = "case-用户登陆失败")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUntil.getSqlSession();
        //获取表loginCase中 id 为2的用户名密码信息
        LoginCase loginCase = session.selectOne("loginCase", 2);
        //获取查表得到的期望结果
        String expectResult = loginCase.getExpected();
        System.out.println("expectResult-期望结果：" + expectResult.toString());

        //请求接口实际结果是
        String result = getResult(loginCase);
        System.out.println("result-实际结果：" + result.toString());

        //将查表获取的期望结果与接口测试的实际值对比
        Assert.assertEquals(result, expectResult);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        // 创建一个 post 请求
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        // 创建 JSON 对象并赋值登陆信息
        JSONObject parm = new JSONObject();
        parm.put("userName", loginCase.getUserName());
        parm.put("password", loginCase.getPassword());
        // 设置请求的 Header 信息
        post.setHeader("Accept", "*/*");
        post.setHeader("Content-type", "application/json");
        // 将参数信息添加到方法中
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        //声明一个对象存储响应结果信息
        String result;
        //执行 post 方法
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        //存储 cookies 信息
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        //System.out.println("cookies信息：" + TestConfig.store);
        //返回结果
        return result;

    }
}
