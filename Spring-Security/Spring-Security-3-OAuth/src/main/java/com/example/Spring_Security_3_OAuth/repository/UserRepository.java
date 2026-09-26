package com.example.Spring_Security_3_OAuth.repository;

import com.example.Spring_Security_3_OAuth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByProviderAndProviderSubject(String provider, String providerSubject);

}
