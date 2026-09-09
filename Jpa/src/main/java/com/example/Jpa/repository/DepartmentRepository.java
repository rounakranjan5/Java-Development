package com.example.Jpa.repository;

import com.example.Jpa.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Department department){
        entityManager.persist(department);
    }

    public Department getDepartmentById(Long id){
        return entityManager.find(Department.class,id);
    }

    // jpql
    public Department getDepartmentByName(String name){
        return entityManager.createQuery("SELECT d FROM Department d WHERE d.name= :name", Department.class).setParameter("name",name).getSingleResult();
    }

}
