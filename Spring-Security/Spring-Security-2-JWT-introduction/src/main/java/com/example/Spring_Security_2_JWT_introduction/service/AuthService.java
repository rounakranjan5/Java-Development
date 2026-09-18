package com.example.Spring_Security_2_JWT_introduction.service;

import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterRequestDto;
import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterResponseDto;
import com.example.Spring_Security_2_JWT_introduction.entity.User;
import com.example.Spring_Security_2_JWT_introduction.repository.RoleRepository;
import com.example.Spring_Security_2_JWT_introduction.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    UserRepository userRepository;
    RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    UserRegisterResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto){
        User user=new User();

        user.setUsername(userRegisterRequestDto.getUsername());
        String encodedPass=passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPass);
        user.setEnabled(true);

        userRepository.save(user);

        UserRegisterResponseDto userRegisterResponseDto=new UserRegisterResponseDto();
        userRegisterResponseDto.setUsername(user.getUsername());
        userRegisterResponseDto.setMessage("registration successful");

        return userRegisterResponseDto;

    }
}
