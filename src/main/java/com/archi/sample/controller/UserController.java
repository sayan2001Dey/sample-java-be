package com.archi.sample.controller;

import com.archi.sample.dto.auth.RegisterResDTO;
import com.archi.sample.dto.user.DeleteUserReqDTO;
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
    public RegisterResDTO deleteUser(@RequestBody DeleteUserReqDTO deleteUserReqDTO) {
        userService.deleteUser(deleteUserReqDTO.getEmail());
        return new RegisterResDTO("Success");
    }
}
