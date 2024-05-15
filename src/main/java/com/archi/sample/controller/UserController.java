package com.archi.sample.controller;

import com.archi.sample.dto.auth.RegisterResDTO;
import com.archi.sample.dto.user.UpdateNameReqDTO;
import com.archi.sample.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping
    public RegisterResDTO updateUser(@RequestBody UpdateNameReqDTO updateUserDTO) {
        userService.updateUser(updateUserDTO.getEmail(), updateUserDTO.getName());
        return new RegisterResDTO("Success");
    }

    @DeleteMapping
    public RegisterResDTO deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return new RegisterResDTO("Success");
    }
}
