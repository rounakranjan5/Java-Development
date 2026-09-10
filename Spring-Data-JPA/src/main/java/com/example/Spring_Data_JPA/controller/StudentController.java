package com.example.Spring_Data_JPA.controller;

import com.example.Spring_Data_JPA.model.Student;
import com.example.Spring_Data_JPA.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student){

        studentService.createStudent(student);

        return ResponseEntity.ok("done");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id){
        Optional<Student> existingStudent= studentService.getStudentById(id);
        return ResponseEntity.ok(existingStudent);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email){
        Student existingStudent=studentService.getStudentByEmail(email);
        return ResponseEntity.ok(existingStudent);
    }

}
