package com.example.Spring_Security_2_JWT_introduction.service;

import com.example.Spring_Security_2_JWT_introduction.entity.CustomUserDetails;
import com.example.Spring_Security_2_JWT_introduction.entity.User;
import com.example.Spring_Security_2_JWT_introduction.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found"));

        return new CustomUserDetails(user);
    }
}
