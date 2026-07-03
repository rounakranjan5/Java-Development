package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private final PaymentService paymentService;

    @Autowired
    OrderService(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    public void orderDetails(){
        System.out.println("Order details");
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order Placed");
    }

}
