package com.sansi.cases;

import com.sansi.config.TestConfig;
import com.sansi.model.AddUserCase;
import com.sansi.model.User;
import com.sansi.untils.DatabaseUntil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue", description = "添加一个用户")
    public void addUser() throws IOException, InterruptedException {
        //获取一个实例
        SqlSession session = DatabaseUntil.getSqlSession();
        //执行 sql--addUserCase 获取增加用户入参
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        //打印增加用户入参
        System.out.println(addUserCase.toString());
        //发请求，获取增加用户结果
        String result = getResult(addUserCase);
        //通过查库获取增加后结果
        Thread.sleep(3000);
        int id = session.selectOne("seleCount");
        System.out.println(id);
        User user = session.selectOne("selcAddUser", id);
        //打印增加后结果
        System.out.println("11111:" + user.toString());
        //判断实际值去预期值是否一致
        Assert.assertEquals(addUserCase.getExpected(), result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException, InterruptedException {

        //打印请求接口地址
        System.out.println(TestConfig.addUserUrl);
        //
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        //将通过addUserCase sql 从数据库读取的参数进行组装，json 格式
        JSONObject parm = new JSONObject();
        parm.put("userName", addUserCase.getUserName());
        parm.put("password", addUserCase.getPassword());
        parm.put("age", addUserCase.getAge());
        parm.put("sex", addUserCase.getSex());
        parm.put("permission", addUserCase.getPermission());
        parm.put("is_delete", addUserCase.getIs_delete());
        //设置请求头
        post.setHeader("Content-Type", "application/json");
        //将参数添加到请求方法中
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);
        //将返回的 cookies进行存储
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //存放返回结果
        String result;
        //发送请求并获得返回结果
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf8");
        System.out.println("00000000" + result);
        return result;
    }
}
