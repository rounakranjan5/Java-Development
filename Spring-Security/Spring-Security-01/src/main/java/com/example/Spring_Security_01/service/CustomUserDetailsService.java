package com.example.Spring_Security_01.service;

import com.example.Spring_Security_01.entity.CustomUserDetails;
import com.example.Spring_Security_01.entity.User;
import com.example.Spring_Security_01.repository.UserRepository;
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

        User user=userRepository.findByName(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found !!"));

        return new CustomUserDetails(user);

    }


}
