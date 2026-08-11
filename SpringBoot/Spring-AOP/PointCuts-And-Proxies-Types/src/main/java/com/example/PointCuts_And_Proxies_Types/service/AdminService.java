package com.example.PointCuts_And_Proxies_Types.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminService {

    public String deleteData(){
        return "Deleted Data : " + LocalDateTime.now().toString();
    }

}
