package org.example;

import org.example.SimpleExampleOfCoreJava.A;
import org.example.SimpleExampleOfCoreJava.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService orderService=context.getBean(OrderService.class);
        orderService.placeOrder();

        // After Run we will get error of BeanCurrentlyInCreationException


        // core java -> here will get stackOverflow error
//        A a=new A();
    }

}
