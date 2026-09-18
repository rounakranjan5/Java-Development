package com.example.Spring_Security_2_JWT_introduction.repository;

import com.example.Spring_Security_2_JWT_introduction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);
}
