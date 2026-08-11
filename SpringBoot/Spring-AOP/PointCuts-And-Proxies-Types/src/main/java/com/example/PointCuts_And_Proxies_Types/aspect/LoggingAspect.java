package com.example.PointCuts_And_Proxies_Types.aspect;

import jdk.jfr.Timestamp;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("within(com.example.PointCuts_And_Proxies_Types.service.Student*)" +
        "&&"+
            "execution(* com.example.PointCuts_And_Proxies_Types.service.Student*.get*(..))"
    )
    public void beforeGetStudent(){
        //empty body
    }

    @Before("beforeGetStudent()")
    public void studentAccessed(){
        System.out.println("beforeGetStudent called...");
    }


    @Before("execution( * com.example.PointCuts_And_Proxies_Types..* (..) )")
    public void beforeLogMethod(){
        System.out.println("Before Log Method called ...");
    }

    @Before("execution(* com.example.PointCuts_And_Proxies_Types.service.Admin*.*(..))")
    public void beforeLogMethodForAdmin(){
        System.out.println("Before Log Method (ADMIN) called ...");
    }

    @After("within(com.example.PointCuts_And_Proxies_Types.service.*)")
    public void cleanupService(){
        System.out.println("Service Cleanup ...");
    }


    @Before("@within(org.springframework.stereotype.Service)")
    public void AtTheRateWithingMethod(){
        System.out.println("@Withing called...");
    }

    @Before("args(com.example.PointCuts_And_Proxies_Types.dto.Student)" + "&&" +
        "within(com.example.PointCuts_And_Proxies_Types.service..*)"
    )
    public void OnlyArgsMethod(){
        System.out.println(" 'args' method called");
    }


    @Before("@args(jdk.jfr.Timestamp)" + "&&" +
            "within(com.example.PointCuts_And_Proxies_Types.service..*)"
    )
    public void AtArgsMethod(){
        System.out.println(" @args method called");
    }



}
