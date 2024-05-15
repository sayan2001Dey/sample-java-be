package com.archi.sample.controller;

import com.archi.sample.dto.auth.RegisterResDTO;
import com.archi.sample.dto.user.UpdateNameReqDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @PatchMapping
    public RegisterResDTO updateName(@RequestBody UpdateNameReqDTO updateNameDTO) {
        return new RegisterResDTO("Success");
    }
}
