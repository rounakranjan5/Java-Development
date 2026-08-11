package com.example.PointCuts_And_Proxies_Types.service;

import com.example.PointCuts_And_Proxies_Types.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student enroll(Student student){
        System.out.println("Service called ...");
        return student;
    }

    public String getAll(){
        return "All Student Data";
    }

    public String updateStud(){
        return "student updated";
    }

}
