package com.example.springbootsample.springsecurity;

import com.example.springbootsample.springsecurity.model.User;

public interface UserService {
    void save(User userName);
    User findByUsername(String username);
}