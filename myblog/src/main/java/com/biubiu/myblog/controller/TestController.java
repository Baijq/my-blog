package com.biubiu.myblog.controller;

import com.biubiu.myblog.util.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * TestController
 *
 * @Author wbbaijq
 * @Date 2020/5/5 2:01
 */
@Api
@RestController
public class TestController {

    @PostMapping("/checklogin")
    @ResponseBody
    @ApiOperation(value = "测试，返回用户信息")
    public RestResponse login(@RequestBody User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")) {
            User user1 = new User();
            user1.setPassword("123456");
            user1.setUsername("admin");
            user1.setName("一抹阳光");
            user1.setIdType(UUID.randomUUID().toString());
            return RestResponse.success().setData(user1);
        } else {
            return RestResponse.failure("用户名密码错误！");
        }
    }
}

@Data
class User {
    private String username;
    private String password;
    private String name;
    private String idType;
}