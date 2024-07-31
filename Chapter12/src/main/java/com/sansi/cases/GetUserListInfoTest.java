package com.sansi.cases;


import com.sansi.config.TestConfig;
import com.sansi.model.GetUserListCase;
import com.sansi.model.User;
import com.sansi.untils.DatabaseUntil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserListInfoTest {


    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户列表信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUntil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase", 1);
        //发送请求获取结果
        JSONArray resultJson = getJsonResult(getUserListCase);
        Thread.sleep(2000);
        //验证结果
        List<User> userList = session.selectList(getUserListCase.getExpected(), getUserListCase);
        //查看是否获取到了数据
        for (User u : userList) {
            System.out.println("list获取的user:" + u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        System.out.println("AAAAAAA" + userListJson.length() + "," + "BBBBBBB" + resultJson.length());
        Assert.assertEquals(userListJson.length(), resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }

    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName", getUserListCase.getUserName());
        param.put("sex", getUserListCase.getSex());
        param.put("age", getUserListCase.getAge());
        //设置请求头信息 设置header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONArray jsonArray = new JSONArray(result);
        System.out.println("调用接口list result:" + result);
        return jsonArray;
    }
}
