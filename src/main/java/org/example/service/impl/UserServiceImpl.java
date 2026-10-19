package org.example.service.impl;

import org.example.common.UserServiceResult;
import org.example.repository.UserRepository;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//这是Service接口的实现类
public class UserServiceImpl implements UserService {
    //从配置文件获取注册相关的参数
    @Value("${web.userService.register.passwordMinLength}")
    private int passwordMinLength;
    @Value("${web.userService.register.passwordMaxLength}")
    private int passwordMaxLength;
    @Value("${web.userService.register.usernameMinLength}")
    private int usernameMinLength;
    @Value("${web.userService.register.usernameMaxLength}")
    private int usernameMaxLength;

    @Autowired
    private UserRepository userRepository;
    @Autowired//不用直接import的而是自己写一个不同名的配置类，方便换接口的实现类还可以实现自动注入
    private PasswordEncoder passwordEncoder;

    //登录操作
    public UserServiceResult login(String username, String password) {
        //检查列表中是否有重复用户名
        User u = checkForUsers(username);
        if (u == null) {
            return new UserServiceResult(400, "用户名或密码错误", null);
        //检查密码散列是否相同
        }else if (!passwordEncoder.matches(password, u.getPassword())){
            return new UserServiceResult(400, "用户名或密码错误", null);
        //通过检测登录成功
        }else{
            return new UserServiceResult(200, "Success", null);
        }
    }

    //注册操作
    public UserServiceResult register(String username, String password) {
        //参数校验的业务逻辑部分，用户名不重复；用户名，密码长度符合要求
        User u = checkForUsers(username);
        if (u != null){
            return new UserServiceResult(400, "用户名已存在", null);
        }else if(username.length() < usernameMinLength || username.length() > usernameMaxLength){
            return new UserServiceResult(400, "用户名必须为" + usernameMinLength + "-" + usernameMaxLength + "位", null);
        }else if(password.length() < passwordMinLength || password.length() > passwordMaxLength){
            return new UserServiceResult(400, "密码必须为" + passwordMinLength + "-" + passwordMaxLength + "位", null);
        }
        /*
        实现添加用户
         */
        //把用户名和密码打包成用户对象
        User newUser = new User();
        newUser.setUsername(username);
        //用passwordEncoder接口定义的方法存散列
        newUser.setPassword(passwordEncoder.encode(password));
        //装到集合中
        userRepository.addUser(newUser.getUsername(), newUser);
        return new UserServiceResult(200, "注册成功", null);
    }

    private User checkForUsers(String username){
        return userRepository.checkForUsers(username);
    }

}
