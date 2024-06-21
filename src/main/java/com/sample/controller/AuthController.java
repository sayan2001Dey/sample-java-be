package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sample.dto.auth.LoginReqDTO;
import com.sample.dto.auth.LoginResDTO;
import com.sample.dto.auth.RegisterReqDTO;
import com.sample.dto.auth.RegisterResDTO;
import com.sample.service.auth.AuthService;

@RestController
@CrossOrigin
@RequestMapping("api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public LoginResDTO login(@RequestBody LoginReqDTO loginReqDTO) {
        String name = authService.login(loginReqDTO.getEmail(), loginReqDTO.getPassword());
        return new LoginResDTO(name);
    }

    @PostMapping("register")
    public RegisterResDTO register(@RequestBody RegisterReqDTO registerReqDTO) {
        authService.register(registerReqDTO.getName(), registerReqDTO.getEmail(),registerReqDTO.getPassword());
        return new RegisterResDTO("Success");
    }
}
