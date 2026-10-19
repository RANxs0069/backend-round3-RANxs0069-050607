package org.example.repository;

import org.example.model.User;

public interface UserRepository {
    public void addUser(String username, User user);
    public User checkForUsers(String username);
}
