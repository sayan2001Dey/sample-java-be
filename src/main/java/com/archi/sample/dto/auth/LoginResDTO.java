package com.archi.sample.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class LoginResDTO {
    @NonNull
    private String name;
}
