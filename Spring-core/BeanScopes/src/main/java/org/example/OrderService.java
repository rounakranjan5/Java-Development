package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class OrderService {

    PaymentService paymentService;

    OrderService(PaymentService paymentService){
        System.out.println("Order Service Started");
        this.paymentService=paymentService;
    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order Placed");
    }

}
