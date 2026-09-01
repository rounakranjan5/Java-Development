package com.example.HibernateDemo.service;

import com.example.HibernateDemo.model.Student;
import com.example.HibernateDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student){
        studentRepository.save(student);
    }

    @Transactional
    public Student getStudentById(Long id){
        Student student=studentRepository.findById(id);

        if(student==null) return null;

        return student;
    }

    @Transactional
    public void updateStudent(Long id,Student student){
        Student existingStudent=studentRepository.findById(id);

        if(existingStudent==null) return;

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
    }

    public void deleteStudent(Long id){
        Student existingStudent=studentRepository.findById(id);

        if(existingStudent==null) return;

        studentRepository.remove(existingStudent);

    }

}
