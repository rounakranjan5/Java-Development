package com.example.PointCuts_And_Proxies_Types.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ApplicationPointcuts {

    @Pointcut("within(com.example.com.example.PointCuts_And_Proxies_Types.controller..*")
    public void controllerLayer(){

    }

    @Pointcut("execution(public * *(..)")
    public void publicMethod(){

    }

    @Pointcut("controllerLayer() && publicMethod()")
    public void publicControllerMethod(){

    }

}
