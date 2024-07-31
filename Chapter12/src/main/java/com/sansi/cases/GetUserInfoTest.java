package com.sansi.cases;

import com.sansi.config.TestConfig;
import com.sansi.model.GetUserInfoCase;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUntil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 1);
        JSONArray resultJson = getJsonResult(getUserInfoCase);
        System.out.println("11111" + resultJson);
        Thread.sleep(2000);
        User user = session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        System.out.println("自己查库获取用户信息:" + user.toString());

        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray resultJson1 = new JSONArray(resultJson.getString(0));

        Assert.assertEquals(jsonArray.toString(), resultJson1.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject parm = new JSONObject();
        parm.put("id", getUserInfoCase.getId());
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        List resuList = Arrays.asList(result);
        JSONArray array = new JSONArray(resuList);
        return array;

    }
}
