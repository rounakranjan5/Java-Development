package com.example.Spring_Security_01.controller;

import com.example.Spring_Security_01.dto.UserRequestDto;
import com.example.Spring_Security_01.dto.UserResponseDto;
import com.example.Spring_Security_01.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello!!";
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(authService.register(userRequestDto));

    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(CsrfToken csrfToken){
//        return csrfToken;
//    }


}
