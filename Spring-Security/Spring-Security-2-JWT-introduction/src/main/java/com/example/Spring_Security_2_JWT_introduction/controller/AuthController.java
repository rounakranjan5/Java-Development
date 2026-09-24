package com.example.Spring_Security_2_JWT_introduction.controller;

import com.example.Spring_Security_2_JWT_introduction.dto.LoginRequestDto;
import com.example.Spring_Security_2_JWT_introduction.dto.LoginResponseDto;
import com.example.Spring_Security_2_JWT_introduction.service.JwtService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    private final ResourceLoader resourceLoader;

    public AuthController(ResourceLoader resourceLoader, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.resourceLoader = resourceLoader;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){

        UsernamePasswordAuthenticationToken authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authentication);

        return new LoginResponseDto(token);

    }

}
