package com.example.Spring_Security_01.repository;

import com.example.Spring_Security_01.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(String name);
}
