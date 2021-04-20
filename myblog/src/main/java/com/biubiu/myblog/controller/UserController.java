package com.biubiu.myblog.controller;

import com.biubiu.myblog.controller.request.AddUserRequest;
import com.biubiu.myblog.controller.request.EditUserRequest;
import com.biubiu.myblog.controller.request.QueryUserRequest;
import com.biubiu.myblog.entity.User;
import com.biubiu.myblog.service.UserService;
import com.biubiu.myblog.util.ParamHelper;
import com.biubiu.myblog.util.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * UserController
 *
 * @author wbbaijq
 */
@Api(tags = "用户api")
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "查询某个用户", notes = "查询用户接口，id:用户id")
    @GetMapping("/{id}")
    public RestResponse getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(user);
        }
    }

    @ApiOperation(value = "查询用户", notes = "查询用户接口，可有有条件")
    @GetMapping
    public Map<String, Object> list(QueryUserRequest request) {
        List<User> userList = null;
        if (request == null) {
            userList = userService.findAll();
        } else {
            userList = userService.find(ParamHelper.converter(request));
        }
        if (CollectionUtils.isEmpty(userList)) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(userList);
        }
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Map<String, Object> remove(@PathVariable("id") Integer id, @RequestParam("db") Boolean db) {
        if (id == null) {
            return RestResponse.failure("id为必传参数");
        }

        if (db != null && db) {
            userService.delete(id);
        } else {
            userService.remove(id);
        }
        return RestResponse.success("删除成功");
    }

    @ApiOperation(value = "新增用户")
    @PostMapping
    public Map<String, Object> save(@RequestBody AddUserRequest request) {
        User user = userService.save(ParamHelper.converter(request));
        if (user != null) {
            return RestResponse.success("添加成功").setData(user);
        } else {
            return RestResponse.failure("添加失败");
        }
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/{id}")
    public Map<String, Object> update(@RequestBody EditUserRequest request, @PathVariable("id") Integer id) {
        if (id == null && request == null) {
            return RestResponse.failure("id为必传参数");
        }
        User user = userService.update(ParamHelper.converter(request, id));
        if (user != null) {
            return RestResponse.success("修改成功").setData(user);
        } else {
            return RestResponse.failure("修改失败");
        }
    }

}
