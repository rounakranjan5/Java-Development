package org.example.Payment;

public class UpiPayment implements PaymentService{

    public UpiPayment() {
        System.out.println("UPI Payment initiated");
    }

    @Override
    public void pay(){
        System.out.println("Payment Done via UPI");
    }

}
