package com.example.Interceptors_Introduction.repository;

import com.example.Interceptors_Introduction.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
