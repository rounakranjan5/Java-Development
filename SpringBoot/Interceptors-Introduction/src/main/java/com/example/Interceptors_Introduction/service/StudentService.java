package com.example.Interceptors_Introduction.service;

import com.example.Interceptors_Introduction.entity.Student;
import com.example.Interceptors_Introduction.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student enrollStudent(Student student){

        Student newStudent=new Student();

        newStudent.setEmail(student.getEmail());
        newStudent.setName(student.getName());

        studentRepository.save(newStudent);

        return newStudent;
    }

}
