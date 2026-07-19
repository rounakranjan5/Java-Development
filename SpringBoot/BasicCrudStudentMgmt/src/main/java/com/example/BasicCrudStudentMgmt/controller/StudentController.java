package com.example.BasicCrudStudentMgmt.controller;


import com.example.BasicCrudStudentMgmt.entity.Student;
import com.example.BasicCrudStudentMgmt.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // ---------- CREATE ---------------

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student newUserdata){
        Student student=studentService.enrollNewStudent(newUserdata);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


    // ----------- READ ----------------

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> data= studentService.displayAllStudents();

        if(data.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id ){

        Optional<Student> student =studentService.getStudent(id);

        if(!student.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(student.get());


    }

    // --------- UPDATE ------------------
    @PutMapping("/student/{id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestBody Student updatedData){

        Student student=studentService.updateStudentData(id,updatedData);

        if(student==null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(student);

    }



    // --------- HARD DELETE ------------------

    @DeleteMapping("/student/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

        Boolean studentresp=studentService.deleteStudentData(id);

        if(studentresp==null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Student with id : "+id+" deleted successfully");

    }


    // -------- SOFT DELETE ------------------

    @PatchMapping("/student/soft-delete")
    public ResponseEntity<String> softDeleteStudent(@RequestParam Long id){

        Boolean isdeleted=studentService.deleteStudentSoftly(id);

        if(!isdeleted) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Student with id : "+id+" marked as DELETED");
    }


}
