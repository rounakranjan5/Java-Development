package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService o1=context.getBean(OrderService.class);
        OrderService o2=context.getBean(OrderService.class);

        System.out.println(o1==o2);// singleton scope -> true and for false for prototype scope
        //Singleton scope means:
        //Spring creates exactly one object for a bean definition inside the Spring container, stores it, and returns the same object whenever needed.

//        PaymentService p1=context.getBean(PaymentService.class);

    }
}