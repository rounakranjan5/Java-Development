package com.example.Spring_Security_2_JWT_introduction.service;


import com.example.Spring_Security_2_JWT_introduction.entity.Role;
import com.example.Spring_Security_2_JWT_introduction.repository.RoleRepository;
import org.springframework.stereotype.Service;

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
