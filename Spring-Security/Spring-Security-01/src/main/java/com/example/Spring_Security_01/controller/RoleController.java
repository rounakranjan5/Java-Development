package com.example.Spring_Security_01.controller;


import com.example.Spring_Security_01.entity.Role;
import com.example.Spring_Security_01.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody Role role){
        roleService.createRole(role);

        return ResponseEntity.ok("role created");
    }


}
