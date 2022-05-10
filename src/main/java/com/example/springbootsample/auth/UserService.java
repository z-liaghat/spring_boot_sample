package com.example.springbootsample.auth;

import com.example.springbootsample.auth.model.User;

public interface UserService {
    void save(User userName);
    User findByUsername(String username);
}