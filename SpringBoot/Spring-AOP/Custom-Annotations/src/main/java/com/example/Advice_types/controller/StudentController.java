package com.example.Advice_types.controller;

import com.example.Advice_types.dto.Student;
import com.example.Advice_types.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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

    @GetMapping
    public ResponseEntity<String> getAll(){
        System.out.println("controlled called ...");
        String resp=studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

}
