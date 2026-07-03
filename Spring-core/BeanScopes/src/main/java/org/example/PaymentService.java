package org.example;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    PaymentService(){
        System.out.println("Payment Service Started");
    }

    void pay(){
        System.out.println("Payment Done");
    }

}
