package com.example.springbootsample.auth;

/**
 * Provide login and autologin after registration
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}