package com.example.Advice_types.aspect;

import com.example.Advice_types.annotation.ResponseTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggingAspect {

    @Before("@annotation(jdk.jfr.Timestamp)")
    public void TimestampLogger(){
        System.out.println("time stamp logger initiated at "+ LocalDateTime.now().toString());
    }

//    @Around("@annotation(com.example.Advice_types.annotation.ResponseTime)")
//    public Object ResponseTimeLogger(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("Response Time Logger Intiated");
//
//        long startTime=System.currentTimeMillis();
//
//        try{
//            return joinPoint.proceed();
//        }
//        finally {
//            System.out.println("Response Time : "+(System.currentTimeMillis()-startTime));
//        }
//
//    }

    @Around("@annotation(responseTime)")
    public Object ResponseTimeLogger(ProceedingJoinPoint joinPoint, ResponseTime responseTime) throws Throwable{
        System.out.println("Response Time Logger Intiated");

        long startTime=System.currentTimeMillis();

        try{
            return joinPoint.proceed();
        }
        finally {

            long duration=System.currentTimeMillis()-startTime;
            String oprn=responseTime.operation();

            if(oprn.isBlank())
                oprn=joinPoint.getSignature().getName();

            long warningThreshold=responseTime.warnAfter();

            if(duration>=warningThreshold){
                System.out.println("SLOW Operation ALERT **  at "+oprn);
            }

            System.out.println("Response Time : "+duration);
        }

    }

}
