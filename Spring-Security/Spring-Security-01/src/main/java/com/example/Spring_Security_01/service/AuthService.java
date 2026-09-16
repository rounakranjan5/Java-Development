package com.example.Spring_Security_01.service;


import com.example.Spring_Security_01.dto.UserRequestDto;
import com.example.Spring_Security_01.dto.UserResponseDto;
import com.example.Spring_Security_01.entity.User;
import com.example.Spring_Security_01.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponseDto register(UserRequestDto userRequestDto){
        User user=new User();

        user.setName(userRequestDto.getName());

        String encodedPassword= passwordEncoder.encode(userRequestDto.getPassword());

        user.setPassword(encodedPassword);

        user.setEnabled(true);


        userRepository.save(user);

        UserResponseDto userResponseDto=new UserResponseDto();

        userResponseDto.setName(user.getName());
        userResponseDto.setMessage("User Registered successfully");

        return userResponseDto;
    }
}
