package com.example.Interceptors_Introduction.controller;

import com.example.Interceptors_Introduction.entity.Student;
import com.example.Interceptors_Introduction.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student){

        System.out.println("Controller Started ...");

        Student newStudent=studentService.enrollStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);

    }

}
