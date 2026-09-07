package com.example.Jpa.service;

import com.example.Jpa.model.Department;
import com.example.Jpa.model.Student;
import com.example.Jpa.repository.DepartmentRepository;
import com.example.Jpa.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService {

    DepartmentRepository departmentRepository;
    StudentRepository studentRepository;

    @Transactional
    public void createdept(Department department){
        departmentRepository.save(department);
    }

    @Transactional
    public void createdept(Department department, String studentName){

        Student student=new Student();
        student.setName(studentName);

        studentRepository.save(student);

        department.addStudent(student);

        departmentRepository.save(department);
    }

}
