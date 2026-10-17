package org.example.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.common.Result;
import org.example.common.UserServiceResult;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    //注入依赖UserService（注入接口而不是实现类）
    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result<User> login(
            @RequestParam
            @NotBlank(message = "用户名或密码不能为空")
            String username,
            @RequestParam
            @NotBlank(message = "用户名或密码不能为空")
            String password) {
        //非空校验已由 @NotBlank 完成，这里只调用业务层并按结果映射响应
        UserServiceResult userServiceResult = userService.login(username, password);
        if (userServiceResult.getCode() == 200) return Result.success(userServiceResult.getMsg());
        else return Result.error(userServiceResult.getCode(), userServiceResult.getMsg());
    }
    //注册
    @PostMapping("/register")
    public Result<?> register(
            @RequestParam
            @NotBlank(message = "用户名或密码不能为空")
            String username,
            @RequestParam
            @NotBlank(message = "用户名或密码不能为空")
            String password) {
        //非空校验已由 @NotBlank 完成，长度校验在 Service 中按配置执行
        UserServiceResult userServiceResult = userService.register(username, password);
        if (userServiceResult.getCode() == 200) return Result.success(userServiceResult.getMsg());
        else return Result.error(userServiceResult.getCode(), userServiceResult.getMsg());
    }
}
