package com.example.Transactions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long toAccountId;
    private Long fromAccountId;
    private Long amount;
    private LocalDate transferredAt;

    public TransferRecord(Long toAccountId, Long fromAccountId, Long amount) {
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.amount = amount;
    }
}
