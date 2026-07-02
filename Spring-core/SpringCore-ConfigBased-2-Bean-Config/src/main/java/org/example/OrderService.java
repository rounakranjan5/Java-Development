package org.example;

import org.example.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class OrderService {

    private final PaymentService paymentService;

    @Autowired
    OrderService(@Qualifier("upiPayment") PaymentService paymentService){
        this.paymentService=paymentService;
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order Placed");
    }

}
