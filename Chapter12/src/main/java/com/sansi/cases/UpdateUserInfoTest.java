package com.sansi.cases;

import com.sansi.config.TestConfig;
import com.sansi.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "根据 userId更新用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUntil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);
        int result = getResult(updateUserInfoCase);
        Thread.sleep(2000);
        int userId = updateUserInfoCase.getUserId();
        User user = session.selectOne(updateUserInfoCase.getExpected(), userId);
        System.out.println(user.toString());
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }


    @Test(dependsOnGroups = "loginTrue", description = "逻辑删除一个用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUntil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);
        int result = getResult(updateUserInfoCase);
        Thread.sleep(2000);
        int userId = updateUserInfoCase.getUserId();
        User user = session.selectOne(updateUserInfoCase.getExpected(), userId);
        System.out.println(user.toString());
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, 1);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject parm = new JSONObject();
        parm.put("id", updateUserInfoCase.getUserId());
        parm.put("userName", updateUserInfoCase.getUserName());
        parm.put("sex", updateUserInfoCase.getSex());
        parm.put("age", updateUserInfoCase.getAge());
        parm.put("permission", updateUserInfoCase.getPermission());
        parm.put("is_delete", updateUserInfoCase.getIs_delete());
        post.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        Integer resultInt = Integer.parseInt(result);
        return resultInt;
    }
}
