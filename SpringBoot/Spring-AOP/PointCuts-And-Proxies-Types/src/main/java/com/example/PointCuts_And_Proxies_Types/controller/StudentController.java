package com.example.PointCuts_And_Proxies_Types.controller;

import com.example.PointCuts_And_Proxies_Types.dto.Student;
import com.example.PointCuts_And_Proxies_Types.service.StudentService;
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
        System.out.println("Student added ...");
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public ResponseEntity<String> getAllStudents(){
        System.out.println("Controller called ...");
        String resp= studentService.getAll();
        System.out.println(resp);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PatchMapping
    public ResponseEntity<String> updateStudent(){
        System.out.println("Controller called ...");
        String resp= studentService.updateStud();
        System.out.println(resp);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }



}
