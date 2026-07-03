package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    // Solution A using Field DI
//    @Autowired
    private OrderService orderService;

    // Solution B using Setter DI
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void pay(){
        System.out.println("Payment Done");
        orderService.getOrderDetails();
    }

}
