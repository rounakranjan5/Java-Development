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
public class StudentService {

    StudentRepository studentRepository;
    DepartmentRepository departmentRepository;

    @Transactional
    public void createStudent(Student student,Long id){

        Department department=departmentRepository.getDepartmentById(id);
        student.setDepartment(department);
        department.getStudents().add(student);
        studentRepository.save(student);

    }

    @Transactional
    public void createStudent(Student student,String deptName){

        Department exisitingDepartment=departmentRepository.getDepartmentByName(deptName);

        if(exisitingDepartment!=null){
            student.setDepartment(exisitingDepartment);
            exisitingDepartment.getStudents().add(student);
            departmentRepository.save(exisitingDepartment);
            studentRepository.save(student);
        }

        else{

            Department department=new Department();
            department.setName(deptName);

            student.setDepartment(department);
            department.getStudents().add(student);
            departmentRepository.save(department);
            studentRepository.save(student);

        }

    }

}
