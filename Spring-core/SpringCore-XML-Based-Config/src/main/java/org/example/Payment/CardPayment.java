package org.example.Payment;

import java.util.List;
import java.util.Map;

public class CardPayment implements PaymentService{

    List<String> applicableCard;
    Map<String,Double> cardToChargesmap;

    public CardPayment(List<String> applicableCard,Map<String,Double>cardToChargesmap) {
        this.applicableCard=applicableCard;
        this.cardToChargesmap=cardToChargesmap;
        System.out.println("Card Payment initiated");
    }

    @Override
    public void pay(){
        System.out.println("--- Applicable cards ---");
        for(String card : applicableCard) System.out.println(card);
        System.out.println("-------");
        System.out.println("Card and their respective charges => ");
        for(Map.Entry<String,Double> entry : cardToChargesmap.entrySet()){
            System.out.println(entry.getKey()+" - $ "+entry.getValue());
        }
        System.out.println("Payment Done via Card");
    }

    public void init(){
        System.out.println("Init method of CardPayment called ...");
    }

    public void cleanup(){
        System.out.println("Cleanup Method of CardPayment called ...");
    }

}
