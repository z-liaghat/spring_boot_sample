package com.example.springbootsample.springsecurity;

/**
 * Provide login and autologin after registration
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}