package com.archi.sample.controller;

import com.archi.sample.dto.auth.LoginReqDTO;
import com.archi.sample.dto.auth.LoginResDTO;
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
    public LoginResDTO login(@RequestBody LoginReqDTO loginReqDTO) {
        String name = authService.login(loginReqDTO.getEmail(), loginReqDTO.getPassword());
        LoginResDTO loginResDTO = new LoginResDTO();
        loginResDTO.setName(name);
        loginResDTO.setError(false);
        return loginResDTO;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterReqDTO registerReqDTO) {
        authService.register(registerReqDTO.getName(), registerReqDTO.getEmail(),registerReqDTO.getPassword())
        return "Success";
    }
}
