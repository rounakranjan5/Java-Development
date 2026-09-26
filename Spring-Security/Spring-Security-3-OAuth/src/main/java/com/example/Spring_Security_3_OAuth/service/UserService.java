package com.example.Spring_Security_3_OAuth.service;

import com.example.Spring_Security_3_OAuth.entity.User;
import com.example.Spring_Security_3_OAuth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User registerOrUpdate(String provider, OidcUser oidcUser){

        String providerSubject=oidcUser.getSubject();
        String name=oidcUser.getClaimAsString("name");
        String email=oidcUser.getClaimAsString("email");

        Optional<User> existingUser=userRepository.findByProviderAndProviderSubject(provider,providerSubject);

        if(existingUser.isPresent()){
            User user=existingUser.get();
            user.setName(name);
            user.setEmail(email);

            return user;

        }

        User user=new User(name,email,provider,providerSubject);

        return userRepository.save(user);

    }

    public Optional<User> findByProviderAndSubject(String provider,String subject){
        return userRepository.findByProviderAndProviderSubject(provider, subject);
    }

}
