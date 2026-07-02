package org.example.Payment;

import org.springframework.stereotype.Component;


public class UpiPayment implements PaymentService{

    @Override
    public void pay() {
        System.out.println("Payment done via UPI");
    }
}
