package com.archi.sample.service;

import com.archi.sample.model.User;
import com.archi.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    public String login(String email, String password) {
        User userData = userRepository.findByEmail(email);
        return userData.getName();
    }

    public boolean register(String name, String email, String password) {
        try {
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return true;
        } catch (Error error) {
            return false;
        }
    }
}
