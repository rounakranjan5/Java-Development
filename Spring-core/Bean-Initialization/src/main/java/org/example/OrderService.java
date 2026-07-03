package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class OrderService {

    public OrderService() {
        System.out.println("Order service started");
    }

    void placeOrder(){
        System.out.println("Order placed");
    }

}
