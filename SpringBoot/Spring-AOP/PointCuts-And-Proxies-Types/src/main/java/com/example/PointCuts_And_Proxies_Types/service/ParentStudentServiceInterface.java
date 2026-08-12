package com.example.PointCuts_And_Proxies_Types.service;

import com.example.PointCuts_And_Proxies_Types.dto.Student;

public interface ParentStudentServiceInterface {
    Student enroll(Student student);
    String getAll();
    String updateStud();
}
