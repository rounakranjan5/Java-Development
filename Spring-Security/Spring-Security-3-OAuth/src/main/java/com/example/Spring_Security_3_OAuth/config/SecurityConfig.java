package com.example.Spring_Security_3_OAuth.config;

import com.example.Spring_Security_3_OAuth.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CustomOidcUserService customOidcUserService
    ){


        httpSecurity.authorizeHttpRequests(
                auth -> auth.requestMatchers("/api/user").permitAll().anyRequest().authenticated())

                .oauth2Login(oauth ->oauth.userInfoEndpoint(userInfo -> userInfo.oidcUserService(customOidcUserService)).defaultSuccessUrl("/api/user/profile",true)
                );

        return httpSecurity.build();
    }

}
