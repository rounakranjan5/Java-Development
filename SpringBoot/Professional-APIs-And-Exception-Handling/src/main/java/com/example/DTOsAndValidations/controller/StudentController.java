package com.example.DTOsAndValidations.controller;


import com.example.DTOsAndValidations.dto.CreateStudentRequestDto;
import com.example.DTOsAndValidations.dto.CreateStudentResponseDto;
import com.example.DTOsAndValidations.dto.UpdateStudentRequestDto;
import com.example.DTOsAndValidations.dto.UpdateStudentResponseDto;
import com.example.DTOsAndValidations.entity.Student;
import com.example.DTOsAndValidations.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // ---------- CREATE ---------------

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto studentRequestDto){
        CreateStudentResponseDto student=studentService.enrollNewStudent(studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


    // ----------- READ ----------------

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents(){
        List<CreateStudentResponseDto> data= studentService.displayAllStudents();

        if(data.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id ){

        CreateStudentResponseDto student =studentService.getStudent(id);

        return ResponseEntity.ok(student);

    }

    // --------- UPDATE ------------------
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id , @RequestBody UpdateStudentRequestDto updateStudentRequestDto){

        UpdateStudentResponseDto student=studentService.updateStudentData(id,updateStudentRequestDto);
        return ResponseEntity.ok(student);

    }



    // --------- HARD DELETE ------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

        studentService.deleteStudentData(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student with id : "+id+" deleted successfully");

    }


    // -------- SOFT DELETE ------------------

    @PatchMapping("/soft-delete")
    public ResponseEntity<String> softDeleteStudent(@RequestParam Long id){

        studentService.deleteStudentSoftly(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student with id : "+id+" marked as DELETED");

    }


}
