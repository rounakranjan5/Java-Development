package com.example.PointCuts_And_Proxies_Types.dto;


import jdk.jfr.Timestamp;

@Timestamp
// timestamp only to demonstrate @args nothing else special about that
public class Student {

    private String name;
    int rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
