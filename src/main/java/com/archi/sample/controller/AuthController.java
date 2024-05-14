package com.archi.sample.controller;

import com.archi.sample.dto.auth.LoginReqDTO;
import com.archi.sample.dto.auth.RegisterReqDTO;
import com.archi.sample.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginReqDTO loginReqDTO) {
        return authService.login(loginReqDTO.getEmail(), loginReqDTO.getPassword());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterReqDTO registerReqDTO) {
        if(authService.register(registerReqDTO.getName(), registerReqDTO.getEmail(),registerReqDTO.getPassword()))
            return "Success";
        return "Couldn't create";
    }
}
