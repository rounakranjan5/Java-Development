package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class PaymentService {

    private final OrderService orderService;

    public PaymentService(OrderService orderService) {
        System.out.println("Processing Payment");
        this.orderService = orderService;
    }

    public void pay(){
        System.out.println("payment done");
        orderService.getOrderDetails();
    }

}
