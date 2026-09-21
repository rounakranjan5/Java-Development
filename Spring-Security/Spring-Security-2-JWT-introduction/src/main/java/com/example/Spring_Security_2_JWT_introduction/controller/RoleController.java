package com.example.Spring_Security_2_JWT_introduction.controller;

import com.example.Spring_Security_2_JWT_introduction.entity.Role;
import com.example.Spring_Security_2_JWT_introduction.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody Role role){
        roleService.createRole(role);
        return ResponseEntity.ok("Role created");
    }

}
