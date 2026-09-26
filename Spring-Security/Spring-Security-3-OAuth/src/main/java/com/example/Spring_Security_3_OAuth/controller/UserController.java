package com.example.Spring_Security_3_OAuth.controller;

import com.example.Spring_Security_3_OAuth.entity.User;
import com.example.Spring_Security_3_OAuth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public String home() {
        return """
                Public Home
                
                Login Using : localhost:8080/oauth2/authorization/google
                """;
    }

    @GetMapping("/profile")
    public Map<String,Object> profile(@AuthenticationPrincipal OidcUser oidcUser){

        User user=userService.findByProviderAndSubject("google", oidcUser.getSubject()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        Map<String,Object> resp=new HashMap<>();

        resp.put("internalUserId",user.getId());

        resp.put(
                "provider",
                user.getProvider()
        );

        resp.put(
                "subject",
                oidcUser.getSubject()
        );

        resp.put(
                "name",
                oidcUser.getClaimAsString("name")
        );

        resp.put(
                "email",
                oidcUser.getClaimAsString("email")
        );

        return resp;

    }
}
