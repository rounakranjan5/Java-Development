package com.example.OrderService.config;

import com.example.OrderService.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @GetMapping("/api/user/{id}")
    User getUserById(@PathVariable("id") Long id);

}