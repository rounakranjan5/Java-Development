package com.example.Spring_Security_2_JWT_introduction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String username;
    private String password;

}
