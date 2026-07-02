package org.example;

import org.example.Payment.CardPayment;
import org.example.Payment.PaymentService;
import org.example.Payment.UpiPayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

   @Bean
   public User createUser(){
      return new User("rounak",22);
   }

   @Bean
   @Primary
   PaymentService CreateCardPayment(){
       return new CardPayment();
   }

   @Bean
   @Qualifier("upi")
   PaymentService createUpiPayment(){
       return new UpiPayment();
   }

   // if custom qualifier name is not provided then by default it is same as the method name like below one CreateCardPayment
   @Bean
   OrderService createOrder(@Qualifier("CreateCardPayment") PaymentService paymentService){
        return new OrderService(paymentService);
   }

//   @Bean
//   OrderService createOrder(@Qualifier("upi") PaymentService paymentService){
//        return new OrderService(paymentService);
//   }

}
