package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final OrderService orderService;

    @Autowired
    PaymentService(OrderService orderService){
        this.orderService=orderService;
    }

    public void pay(){
        System.out.println("Payment Done");
        orderService.orderDetails();
    }

}
