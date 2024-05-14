package com.archi.sample.service;

public interface AuthService {
    public String login(String email, String password);
    public void register(String name, String email, String password);
}
