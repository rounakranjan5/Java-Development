package com.example.Transactions_Part_2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PaymentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long orderId;
    Integer amount;
    Boolean success;

    public PaymentAudit(Long orderId, Integer amount, Boolean success) {
        this.orderId = orderId;
        this.amount = amount;
        this.success = success;
    }
}
