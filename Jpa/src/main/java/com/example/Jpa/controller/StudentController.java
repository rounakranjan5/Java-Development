package com.example.Jpa.controller;

import com.example.Jpa.model.Student;
import com.example.Jpa.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student, @RequestParam Long DeptId){

        studentService.createStudent(student,DeptId);
        return ResponseEntity.ok("done");
    }

    @PostMapping("/withDept")
    public ResponseEntity<String> createStudent(@RequestBody Student student,@RequestParam String deptName){

        studentService.createStudent(student,deptName);
        return ResponseEntity.ok("student added in "+deptName);

    }

}
