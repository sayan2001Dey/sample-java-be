package com.archi.sample.service.user;

import com.archi.sample.model.User;
import com.archi.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void updateUser(String email, String name) {
        User user = userRepository.findByEmail(email);
        user.setName(name);
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }
}
