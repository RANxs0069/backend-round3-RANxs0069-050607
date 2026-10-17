package org.example.repository.impl;

import org.example.repository.UserRepository;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserMapRepositoryImpl implements UserRepository {
    //创建一个集合用于存放用户列表
    private Map<String, User> userTable = new ConcurrentHashMap<>();

    //添加一个用户用于测试登录（任务一）
    //{
    //    userTable.put("Ran",new User("Ran","080404"));
    //}

    //按用户名查询用户信息
    public User checkForUsers(String username) {
        return userTable.get(username);
    }
    //增加用户
    public void addUser(String username, User user) {
        userTable.put(username, user);
    }
}
