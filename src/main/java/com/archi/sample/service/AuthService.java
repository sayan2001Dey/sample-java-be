package com.archi.sample.service;

import com.archi.sample.model.User;

public interface AuthService {
    public String login(String email, String password);
    public boolean register(String name, String email, String password);
}
