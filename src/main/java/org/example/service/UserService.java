package org.example.service;

import org.example.common.UserServiceResult;

public interface UserService {
    public UserServiceResult login(String username, String password);
    public UserServiceResult register(String username, String password);
}
