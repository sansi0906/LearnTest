package com.sansi.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@Api(value = "/", description = "这是我全部的 get 方法.")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "获取 cookies 方法", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获取 cookies 成功～";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户段携带cooikes访问  ", httpMethod = "GET")
    public String getWithCookie(HttpServletRequest Request) {
        Cookie[] cookies = Request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带 cookies 信息过来～";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "这是一个携带 cookie 才能访问的请求。";
            }

        }
        return "你必须携带 cookies 信息过来～";
    }

    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的 get 请求", httpMethod = "GET")
    public Map<String, Integer> getList(@RequestParam Integer start, @RequestParam Integer end) {
        Map<String, Integer> mylist = new HashMap<>();
        mylist.put("梳子", 110);
        mylist.put("帽子", 90);
        mylist.put("围巾", 88);
        return mylist;
    }

    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "需要携带参数才能访问的 get 请求第二种写法", httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start, @PathVariable Integer end) {
        Map<String, Integer> mylist = new HashMap<>();
        mylist.put("hotdog", 3);
        mylist.put("fastfood", 6);
        mylist.put("eggs", 1);
        return mylist;
    }
}
