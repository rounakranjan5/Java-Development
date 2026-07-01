package org.example;

import org.example.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    // Field Dependency Injection
//    @Autowired
//    @Qualifier("card")
//    private PaymentService paymentService;


    private final PaymentService paymentService;
//    private  PaymentService paymentService;

    // Constructor DI ( if there is only one constructor there is no need to add the @Autotwired annotation with that
    @Autowired
    OrderService(@Qualifier("card") PaymentService paymentService){
        this.paymentService=paymentService;
    }

    // @Qualifier has more priority than the @Primary if both are added for a single iterface

    // Setter Dependency Injection
//    @Autowired
//    @Qualifier("card")
//    public void setPaymentService(PaymentService paymentService){
//        this.paymentService=paymentService;
//    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("order placed");
    }

}
