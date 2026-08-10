package com.example.Advice_types.service;

import com.example.Advice_types.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    public Student enroll(Student student){
        System.out.println("Service called ...");
        try {
            throw new RuntimeException("some error occured in enroll method /...");
        }
        finally {
            return student;
        }
//        return "Student Created";
    }


}
