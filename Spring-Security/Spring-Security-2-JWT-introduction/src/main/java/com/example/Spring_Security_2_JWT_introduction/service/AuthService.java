package com.example.Spring_Security_2_JWT_introduction.service;

import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterRequestDto;
import com.example.Spring_Security_2_JWT_introduction.dto.UserRegisterResponseDto;
import com.example.Spring_Security_2_JWT_introduction.entity.Role;
import com.example.Spring_Security_2_JWT_introduction.entity.User;
import com.example.Spring_Security_2_JWT_introduction.repository.RoleRepository;
import com.example.Spring_Security_2_JWT_introduction.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    UserRepository userRepository;
    RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserRegisterResponseDto createUser(UserRegisterRequestDto userRegisterRequestDto){
        User user=new User();

        user.setUsername(userRegisterRequestDto.getUsername());
        String encodedPass=passwordEncoder.encode(userRegisterRequestDto.getPassword());

        user.setPassword(encodedPass);
        user.setEnabled(true);

        Optional<Role> role=roleRepository.findByRoleName("ROLE_USER");
        user.getRoles().add(role.get());

        userRepository.save(user);

        UserRegisterResponseDto userRegisterResponseDto=new UserRegisterResponseDto();
        userRegisterResponseDto.setUsername(user.getUsername());
        userRegisterResponseDto.setMessage("registration successful");

        return userRegisterResponseDto;

    }
}
