package com.archi.sample.controller;

import com.archi.sample.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public String login() {
        System.out.println("r");
        return "test";
    }
}
