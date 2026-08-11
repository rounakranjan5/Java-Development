package com.example.PointCuts_And_Proxies_Types.controller;


import com.example.PointCuts_And_Proxies_Types.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping
    public ResponseEntity<String> deleteData(){
        System.out.println("Admin controller called");

        String resp=adminService.deleteData();

        System.out.println(resp);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resp);

    }

}
