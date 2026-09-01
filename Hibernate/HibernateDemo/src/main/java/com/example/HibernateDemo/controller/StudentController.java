package com.example.HibernateDemo.controller;

import com.example.HibernateDemo.model.Student;
import com.example.HibernateDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.ok("Done");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> showStudentById(@PathVariable Long id){
        Student student= studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id,@RequestBody Student student){
        studentService.updateStudent(id,student);
        return ResponseEntity.ok("Done");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("done");
    }

}
