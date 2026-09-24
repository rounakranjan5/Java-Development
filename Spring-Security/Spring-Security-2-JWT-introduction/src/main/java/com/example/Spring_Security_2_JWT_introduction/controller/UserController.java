package com.example.Spring_Security_2_JWT_introduction.controller;


import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterRequestDto;
import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterResponseDto;
import com.example.Spring_Security_2_JWT_introduction.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication){
        return "Hello! "+authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> createUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        UserRegisterResponseDto resp=authService.createUser(userRegisterRequestDto);
        return ResponseEntity.ok(resp);
    }

}
