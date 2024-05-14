package com.archi.sample.dto.auth;

import lombok.*;

@Setter
@NoArgsConstructor
public class LoginResDTO {
    private boolean error;
    private String name;
}
