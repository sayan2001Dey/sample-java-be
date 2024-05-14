package com.archi.sample.service;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    public String login(String email, String password) {
        return "test";
    }
}
