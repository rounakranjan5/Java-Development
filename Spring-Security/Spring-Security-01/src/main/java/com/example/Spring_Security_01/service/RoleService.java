package com.example.Spring_Security_01.service;


import com.example.Spring_Security_01.repository.RoleRepository;
import org.springframework.stereotype.Service;

import com.example.Spring_Security_01.entity.Role;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(Role role){
        roleRepository.save(role);
    }

}
