package org.example.Payment;

import org.springframework.stereotype.Component;


public class CardPayment implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Payment done via Card");
    }
}
