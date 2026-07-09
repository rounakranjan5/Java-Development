package org.example;

import org.example.Payment.PaymentService;

public class OrderService {

//    private final PaymentService paymentService;
    private PaymentService paymentService;
    private int retryCounts;
    private String gatewayName;


    public OrderService(PaymentService paymentService, int retryCounts , String gatewayName) {
        this.paymentService = paymentService;
        this.retryCounts=retryCounts;
        this.gatewayName=gatewayName;
        System.out.println("Order Service Created");
    }

    public OrderService(){
        System.out.println("Default Order Service created");
    }

    public void setPaymentService(PaymentService paymentService){
        this.paymentService=paymentService;
        System.out.println("Payment gateway added");
    }

    public void placeOrder(){
        System.out.println("Payment Gateway : "+gatewayName+" Activated...");
        paymentService.pay();
        System.out.println("Order placed with "+retryCounts+" retries");
    }

}
