package com.archi.sample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
    @GetMapping("/login")
    public String login() {
        System.out.println("r");
        return "test";
    }
}
