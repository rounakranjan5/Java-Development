package org.example.Payment;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UpiPayment implements PaymentService{

    @Override
    public void pay() {
        System.out.println("Payment done via UPI");
    }
}
