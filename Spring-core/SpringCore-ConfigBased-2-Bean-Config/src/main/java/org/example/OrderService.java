package org.example;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    public void placeOrder(){
        System.out.println("Order Placed by ");
    }

}
