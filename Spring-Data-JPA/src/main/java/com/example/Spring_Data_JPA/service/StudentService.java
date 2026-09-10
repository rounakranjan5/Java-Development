package com.example.Spring_Data_JPA.service;

import com.example.Spring_Data_JPA.model.Student;
import com.example.Spring_Data_JPA.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;

    public void createStudent(Student student){
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student getStudentByEmail(String email){
        Optional<Student> stud=studentRepository.fetchStudentByEmail(email);

        return stud.get();
    }
}
