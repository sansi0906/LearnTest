package com.sansi.server;

import com.sansi.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的 post 请求方法")
@RequestMapping("/v1")
public class MyPostMethod {
    private Cookie cookie;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口,成功后获取 cookie 信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam String userName,
                        @RequestParam String passWord) {
        if (userName.equals("zhangsan") && passWord.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了～";
        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u) {
        User user;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassWord().equals("123456")) {
                user = new User();
                user.setName("lisi");
                user.setAge(11);
                user.setSex("男");
                return user.toString();
            }

        }

        return "参数错误或者不合法";
    }


}
