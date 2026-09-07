package com.example.Jpa.controller;

import com.example.Jpa.model.Department;
import com.example.Jpa.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/dept")
@AllArgsConstructor
public class DepartmentController {

    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody Department department){
        departmentService.createdept(department);
        return ResponseEntity.ok("department created successfully!!");
    }

    @PostMapping("/withStudent")
    public ResponseEntity<String> createDepartment(@RequestBody Department department, @RequestParam String studentName){
        departmentService.createdept(department,studentName);
        return ResponseEntity.ok("department created successfully!!");
    }

}
