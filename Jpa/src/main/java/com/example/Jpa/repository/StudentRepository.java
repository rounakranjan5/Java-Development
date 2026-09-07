package com.example.Jpa.repository;

import com.example.Jpa.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Student student){
        entityManager.persist(student);
    }

}
