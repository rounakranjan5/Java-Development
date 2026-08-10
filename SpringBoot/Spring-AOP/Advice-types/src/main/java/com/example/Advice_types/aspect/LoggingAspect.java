package com.example.Advice_types.aspect;

import com.example.Advice_types.dto.Student;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//    @Before("execution( String com.example.Advice_types.service.StudentService.enroll())")
//    void beforeLogMethod(){
//        System.out.println("before log method called");
//
////        boolean isALlowed=false;
////
////        if(!isALlowed){
////            throw new RuntimeException("Error occurred in Logging Aspect ...");
////        }
//
//    }
//
//    @AfterReturning("execution( String com.example.Advice_types.service.StudentService.enroll())")
//    void afterReturningMethod(){
//        System.out.println("after returning called");
//    }
//
//    @AfterThrowing(value = "execution( String com.example.Advice_types.service.StudentService.enroll())",throwing = "exception")
//    void  afterThrowingMethod(Throwable exception){
//        System.out.println("After Throwing called");
//        System.out.println("Exception Cause : "+exception.getMessage());
//    }
//
//    @After("execution(String com.example.Advice_types.service.StudentService.enroll())")
//    void afterMethod(){
//        System.out.println("After method called");
//    }

@Around("execution(com.example.Advice_types.dto.Student com.example.Advice_types.service.StudentService.enroll(com.example.Advice_types.dto.Student))")
Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

    System.out.println("Around : starting " + joinPoint.getSignature().getName());

    Object[] args = joinPoint.getArgs();

    Student ogStud = (Student) args[0];

    ogStud.setName("TEST");

    System.out.println("args " + ogStud);

    Object result = joinPoint.proceed(args);

    System.out.println("Around done");

    return result;
}

}
