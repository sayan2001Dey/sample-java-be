package com.archi.sample.dto.auth;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonAppend
@Getter
@AllArgsConstructor
public class RegisterResDTO {
    private String message;
}
