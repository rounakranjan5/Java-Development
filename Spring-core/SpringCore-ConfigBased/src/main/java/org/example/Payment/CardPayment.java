package org.example.Payment;

import org.springframework.stereotype.Component;

// custom bean name
@Component("card")
public class CardPayment implements PaymentService{

    @Override
    public void pay() {
        System.out.println("Payment done via CARD");
    }

}
