package org.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    // lets create two definations for payment service if scope is singleton then two different objects are managed by IOC here

//    @Bean
//    PaymentService getPayment(){
//        return new PaymentService();
//    }
//
//    @Bean
//    @Primary
//    PaymentService getPayment2(){
//        return new PaymentService();
//    }

}
