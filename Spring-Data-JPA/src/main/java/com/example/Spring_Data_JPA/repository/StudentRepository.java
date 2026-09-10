package com.example.Spring_Data_JPA.repository;

import com.example.Spring_Data_JPA.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findById(Long id);

    @Query(
            value = """
             SELECT * FROM student WHERE email=:email       
            """
    ,nativeQuery = true)
    Optional<Student> fetchStudentByEmail(@Param("email") String email);

    Page<Student> findAllByName(String name, Pageable pageable);
}
