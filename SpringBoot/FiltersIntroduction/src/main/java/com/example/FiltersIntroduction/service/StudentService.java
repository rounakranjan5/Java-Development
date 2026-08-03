package com.example.FiltersIntroduction.service;

import com.example.FiltersIntroduction.dto.StudentRequestDto;
import com.example.FiltersIntroduction.entity.Student;
import com.example.FiltersIntroduction.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(StudentRequestDto studentRequestDto){

        Student student=new Student();

        student.setEmail(studentRequestDto.getEmail());
        student.setName(studentRequestDto.getName());

        studentRepository.save(student);

//        try{
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return student;
    }

}
