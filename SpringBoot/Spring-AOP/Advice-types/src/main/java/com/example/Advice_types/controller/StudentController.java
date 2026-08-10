package com.example.Advice_types.controller;

import com.example.Advice_types.dto.Student;
import com.example.Advice_types.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student){
        System.out.println("Controller called ...");
        studentService.enroll(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

}
