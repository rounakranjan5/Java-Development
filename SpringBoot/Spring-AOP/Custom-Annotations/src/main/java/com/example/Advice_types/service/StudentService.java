package com.example.Advice_types.service;

import com.example.Advice_types.annotation.ResponseTime;
import com.example.Advice_types.dto.Student;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Timestamp
    @ResponseTime(warnAfter = 1200,operation = "Create Student in Student Service")
    public Student enroll(Student student){
        System.out.println("Student Enrolled ...");
        return student;
    }

    @ResponseTime(warnAfter = 1200,operation = "Get ALL Students in Student Service")
    public String getAllStudents(){
        try{
            Thread.sleep(2000);
        }
        catch(Exception e) {}

        return "All Students DATA";

    }

}
