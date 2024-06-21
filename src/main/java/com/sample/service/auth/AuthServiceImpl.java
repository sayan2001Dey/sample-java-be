package com.sample.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.User;
import com.sample.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    public String login(String email, String password) {
        User userData = userRepository.findByEmail(email);
        return userData.getName();
    }

    public void register(String name, String email, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
    }
}
